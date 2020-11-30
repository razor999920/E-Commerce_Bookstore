import React, { FC } from "react"
import { useOvermind } from "@mnp/overmind"
import { navigate } from "@reach/router"
import { styled } from "@mnp/theme"

const Header: FC = () => {
  const { state: { isLoggedIn }, actions: { signOut } } = useOvermind()

  const handleSignOut = () => {
    signOut().then(() => navigate("/"))
  }

  const handleSignIn = () => {

  }

  return (
    <StyledContainer>
    </StyledContainer>
  )
}

export default Header

const StyledContainer = styled.header`
  padding: 20px 30px;
`
