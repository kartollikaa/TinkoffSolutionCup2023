package com.kartollika.myapplication.card.content

import com.kartollika.myapplication.util.uuid

sealed interface ContentType {

  data class HeaderSubheader(
    val header: String,
    val subHeader: String,
    val image: Int? = null,
  ) : ContentType

  data class Header(
    val header: String,
  ) : ContentType

  data class HorizontalScrollingCards(
    val items: List<Card>,
    val onItemClickListener: (String) -> Unit,
  ) : ContentType {

    data class Card(
      val id: String = uuid,
      val title: String,
      val description: String,
      val image: Int? = null,
    )
  }

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

  data class Cell(
    val title: String,
    val description: String,
    val image: Int? = null,
  ) : ContentType
}
