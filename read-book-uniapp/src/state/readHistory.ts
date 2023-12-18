import { defineStore } from "pinia"
import { reactive, ref } from "vue"

export const useReadHistoryStore = defineStore("read-history", () => {
    const lastReadBookId = ref(uni.getStorageSync("last-read-book-id") as string | undefined)

    const readHistory = reactive(new Map<string, number>())

    loadSorageData()

    function loadSorageData() {
        const history = uni.getStorageSync("read-history")
        if (history) {
            for (const key in history) {
                if (Object.prototype.hasOwnProperty.call(history, key)) {
                    const element = history[key]
                    if (element) {
                        const page = parseInt(element)
                        readHistory.set(key, page)
                    }
                }
            }
        }
    }

    function saveDataToStorage() {
        const data = {} as any
        Array.from(readHistory.entries()).forEach((entrie) => {
            data[entrie[0]] = entrie[1]
        })
        uni.setStorage({
            key: "read-history",
            data,
        })
    }

    function getReadPage(id: string) {
        let page = readHistory.get(id)
        if (typeof page == "string") {
            page = parseInt(page)
        }
        return page as number | undefined
    }

    function setReadPage(id: string, page: number) {
        readHistory.set(id, page)
        saveDataToStorage()
        uni.setStorage({
            key: "last-read-book-id",
            data: id,
        })
    }

    return {
        lastReadBookId,
        getReadPage,
        setReadPage,
    }
})
