import React, { FC, useState } from "react"
import { RouteComponentProps } from "@reach/router"
import { useOvermind } from "@mnp/overmind"
import { styled } from "@mnp/theme"
import { Input } from "@rebass/forms"
import { Box, Button, Flex, Text } from "rebass/styled-components"


const Login: FC<RouteComponentProps> = () => {
  const [login, setLogin] = useState<string>("")
  const [password, setPassword] = useState<string>("")
  const [error, setError] = useState<string>("")
  const [loading, setLoading] = useState<boolean>(false)

  const { state, actions } = useOvermind()

  const handleSubmit = async (e: any) => {
    e.preventDefault()
    setLoading(true)
    const result = await actions.signIn({ login, password })
    if (!result) {
      setLoading(false)
      setError(state.error)
    }
  }

  return (
    <Flex justifyContent="center" alignItems="center" width="100%" height="100%">
      <StyledForm onSubmit={handleSubmit}>
        <Input
          placeholder="Username or email"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
        />
        <br />
        <Input
          placeholder="Password"
          value={password}
          type="password"
          onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <Button>Login</Button>
        {error && <Text color="danger">{error}</Text>}
      </StyledForm>
    </Flex>
  )
}

export default Login

const StyledForm = styled.form`
  max-width: 450px;
  width: 100%;
`

