module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://3abb821f14f3.ngrok.io/",
        secure: false,
      },
    },
  },
}
