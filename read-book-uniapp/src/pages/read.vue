<template>
    <view class="w-screen h-screen bg-neutral-1">
        <!-- 文章内容区域 -->
        <scroll-view class="w-screen h-screen" scroll-y>
            <!-- 下面的class中，text-n表示字体大小，leading-n表示行距，tracking-wider表示字符间距，可以查阅tailwindcss文档 -->
            <view class="p-6 text-5 leading-10 tracking-wider" @click="showController = !showController">
                <view
                    v-if="params.page > 0"
                    class="py-4 text-8 max-w-50% overflow-ellipsis overflow-hidden whitespace-nowrap"
                    >第{{ params.page }}章</view
                >
                <view v-else class="py-4 text-8 max-w-50% overflow-ellipsis overflow-hidden whitespace-nowrap">
                    前言
                </view>
                <view class="whitespace-pre-wrap" style="word-wrap: anywhere">
                    {{ article.content }}
                </view>
                <!-- 底部控制按钮 -->
                <view class="w-full flex justify-between items-center p-3 mt-4" @click.stop>
                    <view
                        @click="prevPage"
                        class="w-22 bg-white text-sm rounded-full flex-center py-1 shadow b-solid b-neutral-1 b-1 b-opacity-50"
                        :class="params.page == 0 ? 'text-gray' : ''"
                        >上一章</view
                    >
                    <view
                        @click="showCatalogue = true"
                        class="w-22 bg-white text-sm rounded-full flex-center py-1 shadow b-solid b-neutral-1 b-1 b-opacity-50"
                        >目录</view
                    >
                    <view
                        @click="nextPage"
                        class="w-22 bg-white text-sm rounded-full flex-center py-1 shadow b-solid b-neutral-1 b-1 b-opacity-50"
                        :class="params.page == article.totalPageSize ? 'text-gray' : ''"
                        >下一章</view
                    >
                </view>
            </view>
        </scroll-view>

        <!-- 左侧目录 -->
        <view
            v-if="showCatalogue"
            @click="showCatalogue = false"
            class="fixed left-1 w-screen z-50 bg-black bg-opacity-40"
            :style="{
                height: showController ? 'calc(100vh - 14rem)' : '100vh',
                top: showController ? '8rem' : '0',
            }"
        >
            <scroll-view scroll-y class="bg-white flex-col h-full max-w-50%" scroll-into-view="actived-page">
                <view
                    @click="router.replace(`/pages/read?id=${params.id}&page=0`)"
                    class="px-2 py-4 b-b-solid b-b b-b-gray"
                    :class="0 == params.page ? 'text-blue' : ''"
                    >{{ article.articleName }}</view
                >

                <view
                    :id="page == params.page ? 'actived-page' : ''"
                    class="px-2 py-4 b-b-solid b-b b-b-gray-3"
                    @click="router.replace(`/pages/read?id=${params.id}&page=${page}`)"
                    :class="page == params.page ? 'text-blue' : ''"
                    v-for="page of pages"
                    :key="page"
                    >第{{ page }}章</view
                >
            </scroll-view>
        </view>

        <!-- 顶部 导航栏 -->
        <view style="height: 8rem" v-if="showController" class="fixed left-0 top-0 w-screen bg-white z-10">
            <view class="absolute h-full left-4 top-0 mt-12">
                <view @click="router.replaceAll('/pages/books')" class="ic-round-home text-2xl"></view>
            </view>

            <view class="flex-center flex-col mt-12">
                <view class="w-40% overflow-ellipsis overflow-hidden whitespace-nowrap">{{ article.articleName }}</view>
                <view class="mt-1 text-xs text-neutral">第{{ params.page }}章</view>
            </view>
        </view>

        <!-- 底部控制按钮 -->
        <view
            v-if="showController"
            style="height: 6rem"
            class="fixed bottom-0 w-full flex justify-between items-center px-6 bg-white z-10"
        >
            <view
                @click="prevPage"
                class="w-22 bg-white text-sm rounded-full flex-center py-1 shadow b-solid b-neutral-1 b-1 b-opacity-50"
                :class="params.page == 0 ? 'text-gray' : ''"
                >上一章</view
            >
            <view
                @click="showCatalogue = true"
                class="w-22 bg-white text-sm rounded-full flex-center py-1 shadow b-solid b-neutral-1 b-1 b-opacity-50"
                >目录</view
            >
            <view
                @click="nextPage"
                class="w-22 bg-white text-sm rounded-full flex-center py-1 shadow b-solid b-neutral-1 b-1 b-opacity-50"
                :class="params.page == article.totalPageSize ? 'text-gray' : ''"
                >下一章</view
            >
        </view>
    </view>
</template>

<script setup lang="ts">
    import { Article, ArticleContent, getArticleContent } from "@/api/api"
    import { useRoute, useRouter } from "uni-mini-router"
    import { computed, ref, watch } from "vue"
    import { useReadHistoryStore } from "@/state/readHistory"
    const router = useRouter()
    const route = useRoute()
    const readHistoryStore = useReadHistoryStore()

    const params = computed(() => {
        return {
            id: route.query?.id,
            page: route.query?.page,
        }
    })

    const article = ref({} as Article & ArticleContent)

    const pages = computed(() => {
        const list = [] as number[]
        if (article.value) {
            for (let i = 1; i <= article.value.totalPageSize; i++) {
                list.push(i)
            }
        }
        return list
    })

    const loading = ref(false)
    const showCatalogue = ref(false)
    const showController = ref(false)

    // loadArticle(params.value.id, params.value.page)

    function loadArticle(id: string, page: any) {
        if (id === undefined || page === undefined || page == "") {
            return
        }
        readHistoryStore.setReadPage(id, page)
        loading.value = true
        getArticleContent(id, page).then((r) => {
            article.value = r
            loading.value = false
            uni.setNavigationBarTitle({
                title: r.articleName,
            })
        })
    }

    watch(
        () => params.value,
        (newVal) => {
            loadArticle(newVal.id, newVal.page)
        }
    )

    function prevPage() {
        if (params.value.id === undefined || params.value.page === undefined) {
            return
        }
        if (params.value.page < 1) {
            return
        }
        router.replace(`/pages/read?id=${params.value.id}&page=${parseInt(params.value.page) - 1}`)
    }

    function nextPage() {
        if (params.value.id === undefined || params.value.page === undefined) {
            return
        }
        if (params.value.page == article.value.totalPageSize) {
            return
        }
        router.replace(`/pages/read?id=${params.value.id}&page=${parseInt(params.value.page) + 1}`)
    }
</script>
