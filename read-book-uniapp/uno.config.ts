// uno.config.ts
import { defineConfig, presetIcons } from "unocss"

import presetWeapp from "unocss-preset-weapp"
import { transformerClass } from "unocss-preset-weapp/transformer"

const transformRules = {
    ".": "-d111-",
    "/": "-s111-",
    ":": "-c111-",
    "%": "-p111-",
    "!": "-e111-",
    "#": "-w111-",
    "(": "-b111l-",
    ")": "-b111r-",
    "[": "-f111l-",
    "]": "-f111r-",
    $: "-r111-",
    ",": "-r222-",
}

export default defineConfig({
    // ...UnoCSS options
    presets: [
        presetIcons({
            prefix: "",
            extraProperties: {
                display: "inline-block",
            },
        }),
        // https://github.com/MellowCo/unocss-preset-weapp
        presetWeapp({
            transformRules,
            whRpx: false,
        }) as any,
    ],

    rules: [["overflow-ellipsis", { "text-overflow": "ellipsis" }]],

    shortcuts: {
        "flex-center": "flex justify-center items-center",
        "size-full": "w-full h-full",
    },

    layers: {
        default: -1,
        base: 0,
        utilities: 1,
        components: 2,
    },

    transformers: [
        // https://github.com/MellowCo/unocss-preset-weapp/tree/main/src/transformer/transformerClass
        transformerClass({
            transformRules,
        }) as any,
    ],
})
