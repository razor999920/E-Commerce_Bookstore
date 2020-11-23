import React, { useState } from "react"
import { Form, Modal, Button } from "semantic-ui-react"
import { useOvermind } from "src/overmind"
import { state } from "src/overmind/state"

export const SignInModal = () => {
  const {
    actions: { toggleSignInModal, signIn },
    state: { error, signInModalOpen, currentUser: user },
  } = useOvermind()

  const [login, setLogin] = useState<string>("")
  const [password, setPassword] = useState<string>("")

  const handleSignIn = async () => {
    await signIn({ login, password })
    if (!error) toggleSignInModal()
    else console.log(error)
    return null
  }

  return (
    <>
      {user == null ? (
        <Modal
          onClose={toggleSignInModal}
          onOpen={toggleSignInModal}
          open={signInModalOpen}
        >
          <Modal.Header>Sign In</Modal.Header>
          <Modal.Content>
            <Form onSubmit={handleSignIn}>
              <Form.Input
                label="Username or Email"
                placeholder="Username or Email"
                name="login"
                value={login}
                onChange={(e, { name, value }) => {
                  if (name === "login") setLogin(value)
                }}
              />
              <Form.Input
                label="Password"
                placeholder="Password"
                type="password"
                name="password"
                value={password}
                onChange={(e, { name, value }) => {
                  if (name === "password") setPassword(value)
                }}
              />
              <Form.Checkbox label="Remember me" />
              <Button type="submit">Submit</Button>
            </Form>
          </Modal.Content>
        </Modal>
      ) : null}
    </>
  )
}
