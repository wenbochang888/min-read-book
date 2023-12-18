"use strict";
const common_vendor = require("../common/vendor.js");
let baseURL = "http://101.33.233.214:8099";
async function getArticleList() {
  const res = await common_vendor.i.request({
    method: "GET",
    url: baseURL + "/article/redis/get/all"
  });
  return res.data;
}
async function getArticleContent(id, page) {
  const res = await common_vendor.i.request({
    method: "GET",
    url: baseURL + `/article/redis/get/content?articleId=${id}&pageSize=${page}`
  });
  return res.data;
}
async function getDownloadInfo() {
  const res = await common_vendor.i.request({
    method: "GET",
    url: baseURL + "/article/redis/get/download"
  });
  return res.data;
}
exports.a = getDownloadInfo;
exports.b = baseURL;
exports.c = getArticleContent;
exports.g = getArticleList;
