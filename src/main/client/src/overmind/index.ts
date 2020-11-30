import { IAction, IConfig, IOnInitialize, IOperator } from "overmind"

import { createActionsHook, createEffectsHook, createHook, createReactionHook, createStateHook } from "overmind-react"

import * as effects from "./effects"
import * as actions from "./actions"
import { state } from "./state"

export const config = {
  effects,
  state,
  actions,
}

export interface Config
  extends IConfig<{
    state: typeof config.state
    actions: typeof config.actions
    effects: typeof config.effects
  }> {}

export interface OnInitialize extends IOnInitialize<Config> {}

export interface Action<Input = void, Output = void>
  extends IAction<Config, Input, Output> {}

export interface AsyncAction<Input = void, Output = void>
  extends IAction<Config, Input, Promise<Output>> {}

export interface Operator<Input = void, Output = Input>
  extends IOperator<Config, Input, Output> {}

export const useOvermind = createHook<Config>()
export const useState = createStateHook<Config>()
export const useAction = createActionsHook<Config>()
export const useEffects = createEffectsHook<Config>()
export const useReaction = createReactionHook<Config>()
