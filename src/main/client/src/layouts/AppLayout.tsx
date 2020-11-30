import React, { FC } from "react"
import { styled } from "@mnp/theme"
import Sticky from "react-stickynode"

const AppLayout: FC = ({ children }) => {
  return (
    <StyledContainer>
      <Sticky enabled={true} innerZ={1000}>
        {children}
      </Sticky>
    </StyledContainer>
  )
}

export default AppLayout

const StyledContainer = styled.div`
  background-color: ${p => p.theme.colors.gray[2]};
  
  @media (max-width: 990px) {
  background-color: ${p => p.theme.colors.white};
  }
`
