import React, { FC } from "react"
import { useDebounce } from "@mnp/hooks"
import { styled } from "@mnp/theme"

interface LoadingProps {
  loading: boolean
}

const Loading: FC<LoadingProps> = ({ loading, children }) => {
  const isLoading = useDebounce(loading, 200)
  return (
    <>
      <StyledContainer isLoading={isLoading} />
      {!isLoading && children}
    </>
  )
}

export default Loading

const StyledContainer = styled.div<{ isLoading: boolean }>`
  position: fixed;
  z-index: 1000;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100vw;
  background-color: white;
  transition: opacity 1s, visibility -0.3s linear 1s;
  
  ${p => p.theme.flex.center};
  visibility: ${p => (p.isLoading ? "visible" : "hidden")};
  opacity: ${p => (p.isLoading ? 1 : 0)};
`