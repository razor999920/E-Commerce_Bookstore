const path = require("path")
const webpack = require("webpack")
const MiniCssExtractPlugin = require("mini-css-extract-plugin")
const HtmlWebpackPlugin = require("html-webpack-plugin")
const FaviconsWebpackPlugin = require("favicons-webpack-plugin")
const { CleanWebpackPlugin } = require("clean-webpack-plugin")

module.exports = {
  mode: "development",
  devtool: "source-map",
  entry: {
    main: [
      path.join(__dirname, "src", "index.tsx"),
    ],
  },
  output: {
    filename: "[name].js",
    path: path.resolve(__dirname, "dist"),
    publicPath: "/",
  },
  resolve: {
    alias: {
      "@mnp": path.join(__dirname, "/src"),
      "@components": path.join(__dirname, "/src/components"),
    },
    modules: ["node_modules", path.resolve(__dirname, "./")],
    extensions: [".ts", ".tsx", ".js", ".json"],
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
        },
      },
      {
        test: /\.(ts|tsx)?$/,
        loader: "awesome-typescript-loader",
        exclude: /node_modules/,
      },
      {
        test: /\.css$/,
        use: [
          {
            loader: MiniCssExtractPlugin.loader,
          },
          "css-loader",
        ],
      },
      {
        test: /\.jpe?g$|\.gif$|\.ico$|\.png$|\.svg$/,
        use: "file-loader?name=[name].[ext]?[hash]",
      },
      {
        test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        loader: "url-loader?limit=10000&mimetype=application/font-woff",
      },
      {
        test: /\.(ttf|eot)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        loader: "file-loader",
      },
      {
        test: /\.otf(\?.*)?$/,
        use:
          "file-loader?name=/fonts/[name].  [ext]&mimetype=application/font-otf",
      },
    ],
  },
  devServer: {
    publicPath: "/",
    port: 3000,
    historyApiFallback: true,
    headers: { "Access-Control-Allow-Origin": "*" },
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        secure: false,
      },
    },
    hot: true,
    noInfo: false,
    quiet: false,
    contentBase: path.join(__dirname, "dist"),
  },
  plugins: [
    new CleanWebpackPlugin(),
    new MiniCssExtractPlugin({
      filename: "style.css",
      chunkFilename: "[id].css",
    }),
    new webpack.HotModuleReplacementPlugin(),
    new FaviconsWebpackPlugin({
      logo: path.join(__dirname, "icon.png"),
      cache: true,
      inject: true,
    }),
    new HtmlWebpackPlugin({
      template: path.join(__dirname, "src", "index.html"),
    }),
  ],
}
