package com.kartollika.myapplication.card.header

/**
 * Отдельный блок, представляющий собой отдельный [Header],
 * который может переиспользоваться для разных блоков
 * Существуют элементы, где хедер не используется, а также блоки, которые имеют общий заголовок
 * Для этих целей существует этот класс, чтобы отобразить заголовок вместе с кнопкой справа
 */
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