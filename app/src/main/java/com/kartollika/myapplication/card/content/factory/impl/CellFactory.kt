package com.kartollika.myapplication.card.content.factory.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.content.factory.ContentFactory
import com.kartollika.myapplication.databinding.CellLayoutBinding

/**
 * Контент типа Cell
 */
internal class CellFactory : ContentFactory<ContentType.Cell> {

  private lateinit var binding: CellLayoutBinding

  override fun provideView(context: Context): View {
    return CellLayoutBinding.inflate(LayoutInflater.from(context)).apply {
      binding = this
    }.root
  }

  override fun applyData(model: ContentType.Cell) {
//    binding.image = model.image
    binding.header.text = model.title
    binding.subheader.text = model.description
  }
}