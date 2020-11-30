import React, { FC } from "react"
import { styled } from "@mnp/theme"

const ErrorFallback: FC = () => {
  return (
    <StyledContainer>
      There has been an error. Check console for more details.
    </StyledContainer>
  )
}

export default ErrorFallback

const StyledContainer = styled.div`
  ${p => p.theme.flex.center};
  height: 100vh;
`
