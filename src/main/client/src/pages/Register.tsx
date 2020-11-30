import React, { FC } from "react"
import { RouteComponentProps } from "@reach/router"

interface RegisterProps extends RouteComponentProps {
  token?: string
}

const Register: FC<RegisterProps> = props => {
  return (
    <div>Register. {props.token}</div>
  )
}

export default Register