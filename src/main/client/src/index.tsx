import React from "react"
import ReactDOM from "react-dom"
import App from "./App"

const ROOT = document.getElementById("root")
ReactDOM.render(<App />, ROOT)

declare let module: { hot: any }
if (module.hot) {
  module.hot.accept()
}