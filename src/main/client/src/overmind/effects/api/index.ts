import axios, { AxiosError, AxiosInstance, AxiosResponse } from "axios"
import { CurrentUser } from "./models"

export type ApiError = AxiosError<
  { errors: string[] } | { error: string } | any
>

export class Api {
  private request: AxiosInstance

  constructor(baseUrl: string) {
    this.request = axios.create({
      baseURL: baseUrl,
    })
  }

  getJwt(): Promise<string> {
    return this.request
      .get<{ jwt: string }>("/auth/jwt")
      .then((response) => this.handleResponse(response))
  }

  login(
    usernameOrEmail: string,
    password: string,
    rememberMe: boolean,
  ): Promise<boolean> {
    return this.request
      .post("/auth/login", {
        usernameOrEmail: usernameOrEmail,
        password: password,
        rememberMe: rememberMe,
      })
      .then((response) => response.status !== 401)
  }

  async logout() {
    const response = await this.request.post("/auth/logout")
    return this.handleResponse(response)
  }

  getCurrentUser(): Promise<CurrentUser> {
    return this.request
      .get<CurrentUser>("/users/current")
      .then((response) => this.handleResponse(response))
  }

  handleResponse(response: AxiosResponse) {
    // TODO: do something useful
    return response.data
  }
}

// TODO: baseURL based on dev mode
export const api = new Api("/api")
