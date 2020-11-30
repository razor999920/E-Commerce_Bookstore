import React, { FC } from "react"
import { Popover, PopoverDisclosure, usePopoverState } from "reakit"
import { styled } from "@mnp/theme"
import Logo from "./Logo"

interface LeftMenuProps {
  logo: string
}

const LeftMenu: FC<LeftMenuProps> = ({ logo }) => {
  const popover = usePopoverState()

  return (
    <LeftMenuContainer>
      <Logo
        imageUrl={logo}
        alt="Mom & Pop logo"
      />

      <MenuContainer>
        <PopoverDisclosure {...popover}>Menu</PopoverDisclosure>
        <Popover>
        </Popover>
      </MenuContainer>

    </LeftMenuContainer>
  )
}

export default LeftMenu

const LeftMenuContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-shrink: 0;
`

const MenuContainer = styled.div`
  display: flex;
  align-items: center;
`
