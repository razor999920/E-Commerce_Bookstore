import React, { FC, FunctionComponent } from "react"
import { useOvermind } from "@mnp/overmind"
import { Redirect, RouteComponentProps } from "@reach/router"
import NotFound from "@mnp/pages/NotFound"

interface ProtectedRouteProps extends RouteComponentProps {
  as: FunctionComponent
  vis: "user" | "admin" | "public"
}

const Route: FC<ProtectedRouteProps> = ({ as: Component, vis, ...props }) => {
  const { state: { currentUser } } = useOvermind()
  const { ...rest } = props

  if (vis === "public")
    return <Component {...rest} />

  if (vis === "user")
    return currentUser ? <Component {...rest} /> : <Redirect to="/login" noThrow />

  if (vis === "admin")
    return currentUser && currentUser.isAdmin ? <Component {...rest} /> : <NotFound />

  return <NotFound />
}

export default Route
