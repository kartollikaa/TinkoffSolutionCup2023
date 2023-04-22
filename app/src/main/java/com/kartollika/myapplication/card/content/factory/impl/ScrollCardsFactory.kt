package com.kartollika.myapplication.card.content.factory.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.content.ContentType.HorizontalScrollingCards.Card
import com.kartollika.myapplication.card.content.factory.ContentFactory
import com.kartollika.myapplication.databinding.ScrollCardsLayoutBinding
import com.kartollika.myapplication.databinding.TinkoffSquareCellListItemBinding
import com.kartollika.myapplication.list.decoration.SpacingItemDecoration
import com.kartollika.myapplication.util.Screen

/**
 * Контент типа ScrollCards
 */
internal class ScrollCardsFactory : ContentFactory<ContentType.HorizontalScrollingCards> {

  private lateinit var binding: ScrollCardsLayoutBinding

  override fun provideView(context: Context): View {
    return ScrollCardsLayoutBinding.inflate(LayoutInflater.from(context)).apply {
      binding = this
    }.root
  }

  override fun applyData(model: ContentType.HorizontalScrollingCards) {
    binding.list.adapter = Adapter(model.onItemClickListener).apply {
      submitList(model.items.map(::ListItem))
    }

    binding.list.addItemDecoration(
      SpacingItemDecoration(
        Screen.dp(8),
        SpacingItemDecoration.Orientation.HORIZONTAL,
      )
    )
  }

  private class Adapter(
    private val onItemClickListener: (String) -> Unit
  ) :
    ListAdapter<ListItem, ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      return ItemViewHolder(
        binding = TinkoffSquareCellListItemBinding.inflate(
          LayoutInflater.from(parent.context),
          parent,
          false
        ),
        onItemClickListener = onItemClickListener
      )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
      holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
      override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.card.id == newItem.card.id
      }

      override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
      }

    }
  }

  private data class ListItem(
    val card: Card
  )

  private class ItemViewHolder(
    private val binding: TinkoffSquareCellListItemBinding,
    onItemClickListener: (String) -> Unit,
  ) : ViewHolder(binding.root) {

    private var item: ListItem? = null

    init {
      binding.root.setOnClickListener {
        item?.let {
          onItemClickListener(it.card.id)
        }
      }
    }

    fun bind(item: ListItem) {
      this.item = item

      binding.title.text = item.card.title
      binding.description.text = item.card.description
//      binding.image = item.card.image
    }
  }
}