module.exports = {
  root: true,
  env: {
    browser: true,
    es6: true,
    node: true,
  },
  globals: {
    _: true,
    require: false,
  },
  extends: ["plugin:vue/essential", "@vue/airbnb"],
  rules: {
    "no-console": "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
    "comma-dangle": "off",
    "no-param-reassign": "off",
    "arrow-parens": "off",
    "max-len": [
      "error",
      {
        code: 300,
        ignoreComments: true,
      },
    ],
    "no-underscore-dangle": "off",
    "vue/no-use-v-if-with-v-for": "off",
    radix: "off",
    "consistent-return": "off",
    semi: ["error", "never"],
    quotes: ["off", "quotes"],
    "vue/multiline-html-element-content-newline": "off",
  },
  parserOptions: {
    parser: "babel-eslint",
  },
}
