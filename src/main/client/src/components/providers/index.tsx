import React, { FC } from "react"

import OvermindProvider from "./OvermindProvider"
import MnpThemeProvider from "./MnpThemeProvider"

const AppProvider: FC = ({ children }) => {
  return (
    <OvermindProvider>
      <MnpThemeProvider>
        {children}
      </MnpThemeProvider>
    </OvermindProvider>
  )
}

export default AppProvider