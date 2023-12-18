<template>
    <scroll-view scroll-y class="w-screen">
        <view
            class="flex flex-col flex-wrap w-full py-4 px-4 b-b-solid b-gray-1 b-1"
            v-for="item of books"
            :key="item.articleId"
        >
            <view class="flex" @click="goReadArticle(item)">
                <img mode="widthFix" class="w-20 mr-4 shadow flex-shrink-0" :src="baseURL + item.articleImage" />
                <view class="flex flex-col">
                    <text selectable="true" user-select="true">书名: {{ item.articleName }}</text>
                    <text selectable="true" user-select="true" class="mt-2 text-gray-6 text-sm"
                        >作者: {{ item.authorName }}</text
                    >
                </view>
            </view>
        </view>
    </scroll-view>
</template>

<script setup lang="ts">
    import { ArticleListItem, ArticleList, baseURL, getArticleList } from "@/api/api"
    import { ref } from "vue"
    import { useRouter } from "uni-mini-router"
    import { useReadHistoryStore } from "@/state/readHistory"

    const readHistoryStore = useReadHistoryStore()
    const router = useRouter()

    const books = ref([] as ArticleList)

    getArticleList().then((r) => (books.value = r))

    function goReadArticle(article: ArticleListItem) {
        if (article.shortLink) {
            wx.navigateToMiniProgram({
                shortLink: article.shortLink,
            })
        } else {
            router.push(
                `/pages/read?id=${article.articleId}&page=${readHistoryStore.getReadPage(article.articleId) ?? 0}`
            )
        }
    }
</script>
