package com.kartollika.myapplication.card.content.factory.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.content.factory.ContentFactory
import com.kartollika.myapplication.databinding.HeaderSubheaderLayoutBinding

internal class HeaderSubheaderFactory(
) : ContentFactory<ContentType.HeaderSubheader> {

  private lateinit var binding: HeaderSubheaderLayoutBinding

  override fun provideView(context: Context): View {
    return HeaderSubheaderLayoutBinding.inflate(LayoutInflater.from(context)).apply {
      binding = this
    }.root
  }

  override fun applyData(model: ContentType.HeaderSubheader) {
//    binding.image = model.image
    binding.header.text = model.header
    binding.subheader.text = model.subHeader
  }
}