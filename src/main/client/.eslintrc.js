module.exports = {
    parser: "@typescript-eslint/parser",
    plugins: ["@typescript-eslint", "react-hooks"],
    extends: [
        "plugin:@typescript-eslint/recommended",
        "prettier",
        "prettier/@typescript-eslint",
        "react-app",
        "plugin:react/recommended"
    ],
    env: {
        es6: true,
        node: true,
    },
    parserOptions: {
        sourceType: "module",
        ecmaVersion: 2018,
    },
    rules: {
        "@typescript-eslint/explicit-member-accessibility": "off",
        "@typescript-eslint/no-var-requires": "off",
        "@typescript-eslint/no-explicit-any": "off",
        "@typescript-eslint/explicit-function-return-type": "off",
        "@typescript-eslint/no-empty-interface": "off",
        "jsx-a11y/anchor-is-valid": "off",
        "no-extend-native": "off",
        "react/prop-types": "off",
        "react/no-unescaped-entities": "off",
        "react-hooks/rules-of-hooks": "off",
        "react-hooks/exhaustive-deps": "off",
    },
    settings: {
        react: {
            version: "detect"
        }
    }
}