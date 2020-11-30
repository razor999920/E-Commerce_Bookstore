import React, { FC } from "react"
import { navigate, RouteComponentProps } from "@reach/router"
import { Box, Button, Flex } from "rebass/styled-components"
import { Input } from "@rebass/forms"

const Home: FC<RouteComponentProps> = () => {
  return (
    <Flex p={2} justifyContent="space-between" alignItems="center">
      <Box>
        Mom&Pop
      </Box>
      <Box flex={1} ml={2}>
        <Input id="search-all" name="search-all" placeholder="Search..."/>
      </Box>
      <Box ml={2}>
        <Button variant="outline" onClick={() => navigate("/login")}>Login</Button>
        <Button variant="outline" ml={2} onClick={() => navigate("/register")}>Sign up</Button>
        <Button variant="primary" ml={2} onClick={() => navigate("/cart")}>Cart</Button>
      </Box>
    </Flex>
  )
}

export default Home

