import React, { FC } from "react"
import { ThemeProvider } from "@mnp/theme"

const MnpThemeProvider: FC = ({ children }) => {
  return (
    <ThemeProvider>
      <>{children}</>
    </ThemeProvider>
  )
}

export default MnpThemeProvider