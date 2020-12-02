import React from "react"
import ReactDOM from "react-dom"
import { createOvermind } from "overmind"
import { Provider as ReactOvermindProvider } from "overmind-react"
import { config } from "./overmind"
import { Router } from "react-router-dom"
import { Pages } from "./pages"
import "semantic-ui-css/semantic.min.css"
import history from "./history"

const ROOT = document.getElementById("root")
const overmind = createOvermind(config)

ReactDOM.render(
  <ReactOvermindProvider value={overmind}>
    <Router history={history}>
      <Pages />
    </Router>
  </ReactOvermindProvider>,
  ROOT,
)

// Hot Module Replacement
//
declare let module: { hot: any }
if (module.hot) {
  module.hot.accept()
}
