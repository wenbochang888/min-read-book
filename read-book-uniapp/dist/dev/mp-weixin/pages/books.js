"use strict";
const common_vendor = require("../common/vendor.js"), api_api = require("../api/api.js"), state_readHistory = require("../state/readHistory.js");
const _sfc_main = /* @__PURE__ */ common_vendor.b({
  __name: "books",
  setup(__props) {
    const readHistoryStore = state_readHistory.u();
    const router = common_vendor.T();
    const books = common_vendor.r([]);
    api_api.g().then((r) => books.value = r);
    function goReadArticle(article) {
      if (article.shortLink) {
        common_vendor.w.navigateToMiniProgram({
          shortLink: article.shortLink
        });
      } else {
        router.push(
          `/pages/read?id=${article.articleId}&page=${readHistoryStore.getReadPage(article.articleId) ?? 0}`
        );
      }
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.j(books.value, (item, k0, i0) => {
          return {
            a: common_vendor.u(api_api.b) + item.articleImage,
            b: common_vendor.t(item.articleName),
            c: common_vendor.t(item.authorName),
            d: common_vendor.k(($event) => goReadArticle(item), item.articleId),
            e: item.articleId
          };
        })
      };
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor.f(_sfc_main, [["__file", "/Users/apple/work/gitee/read-book-uniapp/src/pages/books.vue"]]);
wx.createPage(MiniProgramPage);
