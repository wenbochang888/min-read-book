"use strict";
const common_vendor = require("../common/vendor.js"), api_api = require("../api/api.js"), state_readHistory = require("../state/readHistory.js");
const _sfc_main = /* @__PURE__ */ common_vendor.b({
  __name: "read",
  setup(__props) {
    const router = common_vendor.T();
    const route = common_vendor.E();
    const readHistoryStore = state_readHistory.u();
    const params = common_vendor.m(() => {
      var _a, _b;
      return {
        id: (_a = route.query) == null ? void 0 : _a.id,
        page: (_b = route.query) == null ? void 0 : _b.page
      };
    });
    const article = common_vendor.r({});
    const pages = common_vendor.m(() => {
      const list = [];
      if (article.value) {
        for (let i = 1; i <= article.value.totalPageSize; i++) {
          list.push(i);
        }
      }
      return list;
    });
    const loading = common_vendor.r(false);
    const showCatalogue = common_vendor.r(false);
    const showController = common_vendor.r(false);
    function loadArticle(id, page) {
      if (id === void 0 || page === void 0 || page == "") {
        return;
      }
      readHistoryStore.setReadPage(id, page);
      loading.value = true;
      api_api.c(id, page).then((r) => {
        article.value = r;
        loading.value = false;
        common_vendor.i.setNavigationBarTitle({
          title: r.articleName
        });
      });
    }
    common_vendor.n(
      () => params.value,
      (newVal) => {
        loadArticle(newVal.id, newVal.page);
      }
    );
    function prevPage() {
      if (params.value.id === void 0 || params.value.page === void 0) {
        return;
      }
      if (params.value.page < 1) {
        return;
      }
      router.replace(`/pages/read?id=${params.value.id}&page=${parseInt(params.value.page) - 1}`);
    }
    function nextPage() {
      if (params.value.id === void 0 || params.value.page === void 0) {
        return;
      }
      if (params.value.page == article.value.totalPageSize) {
        return;
      }
      router.replace(`/pages/read?id=${params.value.id}&page=${parseInt(params.value.page) + 1}`);
    }
    return (_ctx, _cache) => {
      return common_vendor.l({
        a: common_vendor.u(params).page > 0
      }, common_vendor.u(params).page > 0 ? {
        b: common_vendor.t(common_vendor.u(params).page)
      } : {}, {
        c: common_vendor.t(article.value.content),
        d: common_vendor.k(prevPage),
        e: common_vendor.p(common_vendor.u(params).page == 0 ? "text-gray" : ""),
        f: common_vendor.k(($event) => showCatalogue.value = true),
        g: common_vendor.k(nextPage),
        h: common_vendor.p(common_vendor.u(params).page == article.value.totalPageSize ? "text-gray" : ""),
        i: common_vendor.k(() => {
        }),
        j: common_vendor.k(($event) => showController.value = !showController.value),
        k: showCatalogue.value
      }, showCatalogue.value ? {
        l: common_vendor.t(article.value.articleName),
        m: common_vendor.k(($event) => common_vendor.u(router).replace(`/pages/read?id=${common_vendor.u(params).id}&page=0`)),
        n: common_vendor.p(0 == common_vendor.u(params).page ? "text-blue" : ""),
        o: common_vendor.j(common_vendor.u(pages), (page, k0, i0) => {
          return {
            a: common_vendor.t(page),
            b: page == common_vendor.u(params).page ? "actived-page" : "",
            c: common_vendor.k(($event) => common_vendor.u(router).replace(`/pages/read?id=${common_vendor.u(params).id}&page=${page}`), page),
            d: common_vendor.p(page == common_vendor.u(params).page ? "text-blue" : ""),
            e: page
          };
        }),
        p: common_vendor.k(($event) => showCatalogue.value = false),
        q: showController.value ? "calc(100vh - 14rem)" : "100vh",
        r: showController.value ? "8rem" : "0"
      } : {}, {
        s: showController.value
      }, showController.value ? {
        t: common_vendor.k(($event) => common_vendor.u(router).replaceAll("/pages/books")),
        v: common_vendor.t(article.value.articleName),
        w: common_vendor.t(common_vendor.u(params).page)
      } : {}, {
        x: showController.value
      }, showController.value ? {
        y: common_vendor.k(prevPage),
        z: common_vendor.p(common_vendor.u(params).page == 0 ? "text-gray" : ""),
        A: common_vendor.k(($event) => showCatalogue.value = true),
        B: common_vendor.k(nextPage),
        C: common_vendor.p(common_vendor.u(params).page == article.value.totalPageSize ? "text-gray" : "")
      } : {});
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor.f(_sfc_main, [["__file", "/Users/apple/work/gitee/read-book-uniapp/src/pages/read.vue"]]);
wx.createPage(MiniProgramPage);
