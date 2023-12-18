"use strict";
const common_vendor = require("../common/vendor.js");
const router = common_vendor._({
  routes: [...[{ "meta": { "tabBar": true }, "path": "/pages/books", "aliasPath": "/", "name": "Books" }, { "meta": { "tabBar": true }, "path": "/pages/download", "name": "Download" }, { "path": "/pages/read", "name": "Read" }]]
  // 路由表信息
});
exports.r = router;
