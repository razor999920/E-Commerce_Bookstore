import React, { FC, FunctionComponent } from "react"
import { RouteComponentProps } from "@reach/router"

interface ProtectedRouteProps extends RouteComponentProps {
  as: FunctionComponent
}

const PublicRoute: FC<ProtectedRouteProps> = ({ as: Component, ...props }) => {
  const { ...rest } = props

  return <Component {...rest} />
}

export default PublicRoute
