package com.kartollika.myapplication.card.content

import com.kartollika.myapplication.util.uuid

sealed interface ContentType {

  /**
   * Блок, содержащий Header, Subheader и картинку
   * Картинка находится слева от надписей, выравнивание по левому краю
   */
  data class HeaderSubheader(
    val header: String,
    val subHeader: String,
    val image: Int? = null,
  ) : ContentType

  /**
   * Блок, содержащий Header
   * Картинка находится справа. Текст расположен слева
   */
  data class Header(
    val header: String,
  ) : ContentType

  /**
   * Блок с горизонтальным списком квадратных элементов
   */
  data class HorizontalScrollingCards(
    val items: List<Card>,
    val onItemClickListener: (String) -> Unit,
  ) : ContentType {

    /**
     *
     */
    data class Card(
      val id: String = uuid,
      val title: String,
      val description: String,
      val image: Int? = null,
    )
  }

  /**
   * Блок с вертикальным списком
   */
  data class VerticalList(
    val items: List<Item>,
    val onItemClickListener: (String) -> Unit,
  ) : ContentType {
    data class Item(
      val id: String = uuid,
      val title: String,
      val description: String,
      val image: Int? = null,
    )
  }

  /**
   * Блок с Title, Description и картинкой
   * Картинка находится слева от надписей, выравнивание по левому краю
   */
  data class Cell(
    val title: String,
    val description: String,
    val image: Int? = null,
  ) : ContentType
}
