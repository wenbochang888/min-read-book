<script setup lang="ts">
    // import { useRouter } from "uni-mini-router"
    // import { useReadHistoryStore } from "@/state/readHistory"

    // const readHistoryStore = useReadHistoryStore()
    // const router = useRouter()
    // const id = readHistoryStore.lastReadBookId ?? "1001"
    // const page = readHistoryStore.getReadPage(id) ?? 0

    import { onLaunch, onShow, onHide } from "@dcloudio/uni-app"
    onLaunch(() => {
        // router.push(`/pages/read?id=${id}&page=${page}`)
        console.log("App Launch")
    })
    onShow(() => {
        console.log("App Show")
    })
    onHide(() => {
        console.log("App Hide")
    })
</script>

<style lang="scss">
    view,
    text,
    img,
    input {
        box-sizing: border-box;
    }

    page {
        width: 100%;
        height: 100%;
    }
</style>
