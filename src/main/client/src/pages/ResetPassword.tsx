import React, { FC } from "react"
import { RouteComponentProps } from "@reach/router"


interface ResetPasswordProps extends RouteComponentProps {
  token?: string
}

const ResetPassword: FC<ResetPasswordProps> = props => {
  return (
    <div>
      reset password
    </div>
  )
}

export default ResetPassword
