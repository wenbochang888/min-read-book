"use strict";
const common_vendor = require("../common/vendor.js"), api_api = require("../api/api.js");
const _sfc_main = /* @__PURE__ */ common_vendor.b({
  __name: "download",
  setup(__props) {
    const downloadInfoList = common_vendor.r([]);
    api_api.a().then((r) => downloadInfoList.value = r);
    return (_ctx, _cache) => {
      return {
        a: common_vendor.j(downloadInfoList.value, (item, k0, i0) => {
          return common_vendor.l({
            a: item.type == "text"
          }, item.type == "text" ? {
            b: common_vendor.t(item.content)
          } : item.type == "img" ? {
            d: common_vendor.u(api_api.b) + item.content
          } : {
            e: common_vendor.t(item.type)
          }, {
            c: item.type == "img",
            f: item.content
          });
        })
      };
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor.f(_sfc_main, [["__file", "/Users/apple/work/gitee/read-book-uniapp/src/pages/download.vue"]]);
wx.createPage(MiniProgramPage);
