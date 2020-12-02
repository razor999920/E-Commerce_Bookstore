import React from "react"
import { RouteComponentProps, withRouter } from "react-router-dom"
import { Button, Container, Menu, Segment } from "semantic-ui-react"
import { useOvermind } from "src/overmind"

type Props = {
  title?: string
} & RouteComponentProps

const Nav = ({ title }: Props) => {
  const {
    actions: { signInClicked },
    state: { isLoggedIn, currentUser },
  } = useOvermind()

  return (
    <Segment inverted textAlign="center" style={{ padding: "1em 0em" }}>
      <Menu inverted pointing secondary size="large">
        <Container>
          {title ? <Menu.Item as="a">{title}</Menu.Item> : null}
          <Menu.Item position="right">
            {isLoggedIn ? (
              <Button as="a">{currentUser.username}</Button>
            ) : (
              <>
                <Button as="a" inverted onClick={() => signInClicked()}>
                  Sign in
                </Button>
                <Button as="a" inverted primary style={{ marginLeft: "0.5em" }}>
                  Sign up
                </Button>
              </>
            )}
          </Menu.Item>
        </Container>
      </Menu>
    </Segment>
  )
}

export const Navigation = withRouter(Nav)
