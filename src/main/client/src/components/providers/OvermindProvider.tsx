import React, { FC, Fragment } from "react"
import { Provider } from "overmind-react"
import { createOvermind } from "overmind"
import { config } from "@mnp/overmind"

const overmind = createOvermind(config, {
  devtools: false // TODO enable dev tools
})

const OvermindProvider: FC = ({ children }) => {
  return (
    <Provider value={overmind}>
      <Fragment>{children}</Fragment>
    </Provider>
  )
}

export default OvermindProvider