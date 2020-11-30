import normalize from "styled-normalize"

import { createGlobalStyle } from "."

import CircularStdBlack from "../assets/fonts/CircularStd-Black.woff2"
import CircularStdBold from "../assets/fonts/CircularStd-Bold.woff2"
import CircularStdMedium from "../assets/fonts/CircularStd-Medium.woff2"
import CircularStdBook from "../assets/fonts/CircularStd-Book.woff2"

export const GlobalStyles = createGlobalStyle`
  ${normalize} 
  
  @font-face {
    font-family: CircularStdBlack;
    src: url(${CircularStdBlack});
    font-style: normal; 
    font-weight: normal;
  }
  
  @font-face {
    font-family: CircularStdBold;  
    src: url(${CircularStdBold});
    font-style: normal;
    font-weight: normal;
  }
  
  @font-face {
    font-family: CircularStdMedium;  
    src: url(${CircularStdMedium});
    font-style: normal;
    font-weight: normal;
  }
  
  @font-face {
    font-family: CircularStdBook;  
    src: url(${CircularStdBook});
    font-style: normal;
    font-weight: normal;
  }
`