# 阅读小程序

## 使用技术

uniapp + vue3 + typescript + unocss

## 运行方式

1. 配置 node（推荐 16.20.2 及以上版本）
2. 配置 pnpm：`npm install -g pnpm`
3. 安装依赖：`pnpm i`
4. 开发模式运行：`pnpm dev:mp-weixin`
5. 编译：`pnpm build:mp-weixin`

开发和编译会在项目根目录下的 dist 目录下生成对应文件夹，使用微信开发者工具导入即可查看，
项目根目录有一份编译好的`mp-weixin`，可直接导入微信开发者工具

## 关于服务器地址

可以在 `/src/api/api.ts` 中的第一行找到并修改，现在使用的是 IP 模式

## 关于小程序打开默认进入阅读的书

可以在 `/src/App.vue` 中找到这么一行代码：`const id = readHistoryStore.lastReadBookId ?? "1001"` ， `"1001"` 表示默认进入阅读的书的 ID，可以直接修改

## 推荐使用 vscode 开发

推荐插件在`.vscode`文件夹下面的`extensions.json`

## 关于文章字体

查看`/src/pages/read.vue`中的注释
