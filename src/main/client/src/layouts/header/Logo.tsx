import React, { FC } from "react"
import { navigate } from "@reach/router"
import { styled } from "@mnp/theme"

interface LogoProps {
  imageUrl: string,
  alt: string
  onClick?: () => void
}

const Logo: FC<LogoProps> = ({ imageUrl, alt, onClick }) => {

  const onLogoClick = () => {
    navigate("/")
  }

  return (
    <LogoContainer onClick={onLogoClick}>
      <LogoImage src={imageUrl} alt={alt} />
    </LogoContainer>
  )
}

export default Logo

const LogoContainer = styled.span`
  color: ${p => p.theme.colors.text};
  font-size: 26px;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
`

const LogoImage = styled.img`
  display: block;
  backface-visibility: hidden;
  max-width: 150px;
`
