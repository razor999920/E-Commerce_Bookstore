import React, { FC } from "react"
import * as SC from "styled-components"

import { defaultTheme, Theme } from "./defaultTheme"

const {
  default: styled,
  css,
  ServerStyleSheet,
  StyleSheetManager,
  withTheme,
  createGlobalStyle,
  keyframes,
} = SC as SC.ThemedStyledComponentsModule<Theme>

interface ThemeProviderProps {
  theme?: Partial<Theme>
}

const ThemeProvider: FC<ThemeProviderProps> = ({ theme, children }) => {
  return (
    <SC.ThemeProvider theme={defaultTheme}>
      <>{children}</>
    </SC.ThemeProvider>
  )
}

export {
  createGlobalStyle,
  css,
  defaultTheme,
  keyframes,
  ServerStyleSheet,
  StyleSheetManager,
  styled,
  Theme,
  ThemeProvider,
  withTheme,
}
