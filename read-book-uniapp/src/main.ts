import { createSSRApp } from "vue"
import App from "./App.vue"
import "uno.css"
import { createPinia } from "pinia"
import { router } from "./router"

export function createApp() {
    const pinia = createPinia()

    const app = createSSRApp(App)

    app.use(router).use(pinia)

    return {
        app,
    }
}
