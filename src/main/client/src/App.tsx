import React, { FC, Suspense } from "react"
import { ErrorBoundary } from "react-error-boundary"
import { Router } from "@reach/router"

import { GlobalStyles } from "@mnp/theme/globalStyles"
import { Box } from "rebass/styled-components"
import ErrorFallback from "@components/ErrorFallback"
import AppProvider from "@components/providers"
import Loading from "@components/Loading"
import NotFound from "@mnp/pages/NotFound"
import Home from "@mnp/pages/Home"
import Login from "@mnp/pages/Login"
import Register from "@mnp/pages/Register"
import ForgotPassword from "@mnp/pages/ForgotPassword"
import ResetPassword from "@mnp/pages/ResetPassword"
import UserSettings from "@mnp/pages/UserSettings"
import Route from "@components/Route"
import Cart from "@mnp/pages/Cart"

const App: FC = () => {
  const errorHandler = (e: Error) => console.log(e)

  return (
    <>
      <GlobalStyles />
      <AppProvider>
        <ErrorBoundary onError={errorHandler} FallbackComponent={ErrorFallback}>
          <Suspense fallback={<Loading loading={true} />}>
            <Router>
              <Route vis="public" as={Home} path="/" />
              <Route vis="public" as={Cart} path="/cart" />
              <Route vis="public" as={Login} path="/login" />
              <Route vis="public" as={Register} path="/register" />
              <Route vis="public" as={ForgotPassword} path="/forgot-password" />
              <Route vis="public" as={ResetPassword} path="/reset-password:token" />
              <Route vis="user" as={UserSettings} path="/settings" />
              <NotFound default={true} />
            </Router>
          </Suspense>
        </ErrorBoundary>
      </AppProvider>
    </>
  )
}

export default App
