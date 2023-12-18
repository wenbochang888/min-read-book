"use strict";
Object.defineProperty(exports, Symbol.toStringTag, { value: "Module" });
const common_vendor = require("./common/vendor.js"), router_index = require("./router/index.js");
if (!Math) {
  "./pages/books.js";
  "./pages/download.js";
  "./pages/read.js";
}
const _sfc_main = /* @__PURE__ */ common_vendor.b({
  __name: "App",
  setup(__props) {
    common_vendor.o(() => {
      console.log("App Launch");
    });
    common_vendor.c(() => {
      console.log("App Show");
    });
    common_vendor.e(() => {
      console.log("App Hide");
    });
    return () => {
    };
  }
});
const App = /* @__PURE__ */ common_vendor.f(_sfc_main, [["__file", "/Users/apple/work/gitee/read-book-uniapp/src/App.vue"]]);
function createApp() {
  const pinia = common_vendor.g();
  const app = common_vendor.h(App);
  app.use(router_index.r).use(pinia);
  return {
    app
  };
}
createApp().app.mount("#app");
exports.createApp = createApp;
