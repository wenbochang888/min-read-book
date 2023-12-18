<template>
    <scroll-view class="w-screen" scroll-y>
        <view class="w-full flex flex-col">
            <view
                class="py-4 flex items-center b-b-solid b-3 b-gray"
                v-for="item of downloadInfoList"
                :key="item.content"
            >
                <view class="flex justify-center w-full px-6 text-4 text-gray">
                    <text selectable="true" user-select="true" v-if="item.type == 'text'">{{ item.content }}</text>
                    <image
                        mode="heightFix"
                        show-menu-by-longpress="true"
                        draggable="true"
                        v-else-if="item.type == 'img'"
                        :src="baseURL + item.content"
                    ></image>
                    <text v-else>不支持的类型，type：{{ item.type }}</text>
                </view>
            </view>
        </view>
    </scroll-view>

    <view>
        <official-account style=""></official-account>
    </view>
</template>

<script setup lang="ts">
    import { DownloadInfo, baseURL, getDownloadInfo } from "@/api/api"
    import { ref } from "vue"
    const downloadInfoList = ref([] as DownloadInfo[])

    getDownloadInfo().then((r) => (downloadInfoList.value = r))
</script>
