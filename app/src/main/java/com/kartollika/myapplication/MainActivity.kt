package com.kartollika.myapplication

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.kartollika.myapplication.card.footer.Footer
import com.kartollika.myapplication.card.TinkoffCard
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.header.Header
import com.kartollika.myapplication.databinding.MainActivityBinding

class MainActivity : Activity() {

  private lateinit var binding: MainActivityBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActivityBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    binding.cardHeaderSubheader.apply {
      setContent(ContentType.HeaderSubheader("Button", "MySubheader"))
      setFooter(Footer("Button") {
        Toast.makeText(context, "Footer clicked", Toast.LENGTH_SHORT).show()
      })
    }

    binding.cardHeader.apply {
      setContent(ContentType.Header("Button"))
      setFooter(Footer("Button") {
        showNotificationAboutClick("Footer clicked")
      })
    }

    binding.cardScrollCards.apply {
      setHeader(
        Header(
          header = "Header",
          action = Header.Action.TextButton(
            text = "Hit me",
            action = {
              showNotificationAboutClick("Header textbutton clicked")
            }
          ))
      )

      setContent(
        ContentType.HorizontalScrollingCards(
          items = listOf(
            ContentType.HorizontalScrollingCards.Card(
              title = "Title 1",
              description = "Description 1"
            ),
            ContentType.HorizontalScrollingCards.Card(
              title = "Title 2",
              description = "Description 2"
            ),
            ContentType.HorizontalScrollingCards.Card(
              title = "Title 1",
              description = "Description 3"
            )
          ),
          onItemClickListener = {
            showNotificationAboutClick("Item $it clicked")
          }
        )
      )
    }

    binding.cardVerticalList.apply {
      setHeader(Header(header = "Header"))
      setContent(
        ContentType.VerticalList(
          items = listOf(
            ContentType.VerticalList.Item(
              title = "Title1",
              description = "Descpription1"
            ),
            ContentType.VerticalList.Item(
              title = "Title2",
              description = "Descpription2"
            ),
            ContentType.VerticalList.Item(
              title = "Title3",
              description = "Descpription3"
            ),
            ContentType.VerticalList.Item(
              title = "Title4",
              description = "Descpription4"
            ),
            ContentType.VerticalList.Item(
              title = "Title5",
              description = "Descpription5"
            )
          ),
          onItemClickListener = {
            showNotificationAboutClick("Item $it clicked")
          }
        )
      )
    }
  }

  private fun TinkoffCard.showNotificationAboutClick(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
  }
}