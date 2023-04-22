package com.kartollika.myapplication.card.header

data class Header(
  val header: String,
  val action: Action? = null
) {
  sealed interface Action {
    data class TextButton(
      val text: String,
      val action: () -> Unit = {},
    ): Action
  }
}