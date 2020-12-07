module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://4ef90ec9be69.ngrok.io/#/",
        secure: false,
      },
    },
  },
}
