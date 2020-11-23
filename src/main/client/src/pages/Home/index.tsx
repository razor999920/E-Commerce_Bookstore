import React from "react"
import { Navigation } from "../common/Navigation"
import { SignInModal } from "src/components/SignInModal"

export const Home: React.FC = () => {
  return (
    <>
      <Navigation title="Mom&Pop" />
      <SignInModal />
    </>
  )
}
