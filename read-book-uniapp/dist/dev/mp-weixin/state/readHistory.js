"use strict";
const common_vendor = require("../common/vendor.js");
const useReadHistoryStore = common_vendor.d("read-history", () => {
  const lastReadBookId = common_vendor.r(common_vendor.i.getStorageSync("last-read-book-id"));
  const readHistory = common_vendor.a(/* @__PURE__ */ new Map());
  loadSorageData();
  function loadSorageData() {
    const history = common_vendor.i.getStorageSync("read-history");
    if (history) {
      for (const key in history) {
        if (Object.prototype.hasOwnProperty.call(history, key)) {
          const element = history[key];
          if (element) {
            const page = parseInt(element);
            readHistory.set(key, page);
          }
        }
      }
    }
  }
  function saveDataToStorage() {
    const data = {};
    Array.from(readHistory.entries()).forEach((entrie) => {
      data[entrie[0]] = entrie[1];
    });
    common_vendor.i.setStorage({
      key: "read-history",
      data
    });
  }
  function getReadPage(id) {
    let page = readHistory.get(id);
    if (typeof page == "string") {
      page = parseInt(page);
    }
    return page;
  }
  function setReadPage(id, page) {
    readHistory.set(id, page);
    saveDataToStorage();
    common_vendor.i.setStorage({
      key: "last-read-book-id",
      data: id
    });
  }
  return {
    lastReadBookId,
    getReadPage,
    setReadPage
  };
});
exports.u = useReadHistoryStore;
