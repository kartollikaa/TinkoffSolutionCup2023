package com.kartollika.myapplication.card.footer

/**
 * Отдельный блок, представляющий собой отдельный [Footer],
 * который может переиспользоваться для разных блоков
 * Почти всегда это кнопка, но если нужно поддержать другие варианта футеров,
 * то можно переделать по аналогии с контентом на паттерн "фабрика"
 */
data class Footer(
  val title: String = "Button",
  val onClickListener: () -> Unit = {},
)