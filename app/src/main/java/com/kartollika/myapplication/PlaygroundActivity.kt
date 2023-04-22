package com.kartollika.myapplication

import android.R
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.footer.Footer
import com.kartollika.myapplication.card.header.Header
import com.kartollika.myapplication.databinding.PlaygroundActivityBinding


class PlaygroundActivity : Activity(), AdapterView.OnItemSelectedListener {

  private lateinit var binding: PlaygroundActivityBinding
  private var content: ContentType = ContentType.Header(header = "Header")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = PlaygroundActivityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.footerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
      if (!isChecked) {
        binding.card.setFooter(null)
      } else {
        binding.card.setFooter(
          Footer(
            title = "Title",
            onClickListener = {
            }
          )
        )
      }
    }

    binding.headerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
      if (!isChecked) {
        binding.card.setHeader(null)
      } else {
        binding.card.setHeader(
          Header(
            header = "Header",
            action = Header.Action.TextButton(
              text = "Button"
            )
          ),
        )
      }
    }

    binding.flatSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
      binding.card.setFlat(isChecked)
    }

    binding.spinner.onItemSelectedListener = this

    // Spinner Drop down elements

    // Spinner Drop down elements
    val categories: MutableList<String> = ArrayList()
    categories.add("Cell")
    categories.add("Header")
    categories.add("Header + Subheader")
    categories.add("Horizontal cards")
    categories.add("Vertical list")

    // Creating adapter for spinner

    // Creating adapter for spinner
    val dataAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, categories)

    // Drop down layout style - list view with radio button

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

    // attaching data adapter to spinner

    // attaching data adapter to spinner
    binding.spinner.adapter = dataAdapter
  }

  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    val content = when (position) {
      0 -> ContentType.Cell(
        title = "Title",
        description = "Description",
      )

      1 -> ContentType.Header(
        header = "Header"
      )

      2 -> ContentType.HeaderSubheader(
        header = "Header",
        subHeader = "Subheader"
      )

      3 -> ContentType.HorizontalScrollingCards(
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
//          showNotificationAboutClick("Item $it clicked")
        }
      )

      4 -> ContentType.VerticalList(
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
//          showNotificationAboutClick("Item $it clicked")
        }
      )

      else -> error("Nothing to see here")
    }

    if (this.content == content) return
    binding.card.setContent(content)
  }

  override fun onNothingSelected(parent: AdapterView<*>?) {
  }
}