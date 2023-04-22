package com.kartollika.myapplication.card.footer

data class Footer(
  val title: String = "Button",
  val onClickListener: () -> Unit = {},
)