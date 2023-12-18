export let baseURL = "http://101.33.233.214:8099"

export type Article = {
    articleId: string // ID
    articleName: string // 名字
    articleImage: string // 图片URL
    authorName: string // 作者
    current: number // 无需关心
    totalPageSize: number // 总页数
}

export type ArticleListItem = Article & {
    shortLink?: string // 小程序短链
}

export type ArticleList = ArticleListItem[]

export type DownloadInfo = {
    type: "text" | "img"
    content: string
}

export type ArticleContent = {
    content: string // 文章某一页内容
}

export async function getArticleList() {
    const res = await uni.request({
        method: "GET",
        url: baseURL + "/article/redis/get/all",
    })
    return res.data as ArticleList
}

export async function getArticleContent(id: string, page: number) {
    const res = await uni.request({
        method: "GET",
        url: baseURL + `/article/redis/get/content?articleId=${id}&pageSize=${page}`,
    })
    return res.data as Article & ArticleContent
}

export async function getDownloadInfo() {
    const res = await uni.request({
        method: "GET",
        url: baseURL + "/article/redis/get/download",
    })
    return res.data as DownloadInfo[]
}
