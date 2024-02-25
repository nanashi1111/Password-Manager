package com.passwordmanager.domain

sealed class State<out T> {
  object LoadingState : State<Nothing>()

  object IdleState: State<Nothing>()
  data class ErrorState(var exception: Throwable) : State<Nothing>()
  data class DataState<T>(var data: T) : State<T>()
}