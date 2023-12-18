// Vite中文网：https://vitejs.cn/config/
import { UserConfig } from "vite"
import { resolve } from "path"
import uni from "@dcloudio/vite-plugin-uni"
import Unocss from "unocss/vite"
import TransformPages from "uni-read-pages-vite" // vite.config.ts

export default (): UserConfig => {
    return {
        base: "./",
        // 设置路径别名
        resolve: {
            alias: {
                "@": resolve("./src"),
            },
            extensions: [".js", ".json", ".ts", ".vue"], // 使用路径别名时想要省略的后缀名，可以自己 增减
        },
        // 自定义全局变量
        define: {
            "process.env": {},
            ROUTES: new TransformPages().routes,
        },
        // 构建配置
        build: {
            outDir: "dist",
            chunkSizeWarningLimit: 1500,
            rollupOptions: {
                output: {
                    entryFileNames: `assets/[name].${new Date().getTime()}.js`,
                    chunkFileNames: `assets/[name].${new Date().getTime()}.js`,
                    assetFileNames: `assets/[name].${new Date().getTime()}.[ext]`,
                    compact: true,
                },
            },
        },
        // 代理服务器
        server: {
            proxy: {
                "/article": "http://101.33.233.214:8099/",
            },
        },
        // 插件
        plugins: [uni(), Unocss()],
    }
}
