package com.kartollika.myapplication.card.content.factory.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.content.factory.ContentFactory
import com.kartollika.myapplication.databinding.TinkoffWidthListItemBinding
import com.kartollika.myapplication.databinding.VerticalListLayoutBinding

internal class VerticalListFactory(
) : ContentFactory<ContentType.VerticalList> {

  private lateinit var binding: VerticalListLayoutBinding

  override fun provideView(context: Context): View {
    return VerticalListLayoutBinding.inflate(LayoutInflater.from(context)).apply {
      binding = this
    }.root
  }

  override fun applyData(model: ContentType.VerticalList) {
    binding.list.adapter = Adapter(model.onItemClickListener).apply {
      submitList(model.items.map(::ListItem))
    }
  }

  private class Adapter(
    private val onItemClickListener: (String) -> Unit,
  ) : ListAdapter<ListItem, ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      return ItemViewHolder(
        binding = TinkoffWidthListItemBinding.inflate(
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
        return oldItem.item.id == newItem.item.id
      }

      override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
      }

    }
  }

  private data class ListItem(
    val item: ContentType.VerticalList.Item
  )

  private class ItemViewHolder(
    private val binding: TinkoffWidthListItemBinding,
    onItemClickListener: (String) -> Unit,
  ) : ViewHolder(binding.root) {

    private var item: ListItem? = null

    init {
      binding.root.setOnClickListener {
        item?.let {
          onItemClickListener(it.item.id)
        }
      }
    }

    fun bind(item: ListItem) {
      this.item = item

      binding.title.text = item.item.title
      binding.description.text = item.item.description
//      binding.image = item.item.title
    }
  }
}