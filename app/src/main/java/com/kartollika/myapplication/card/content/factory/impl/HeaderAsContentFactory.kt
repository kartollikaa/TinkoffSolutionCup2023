package com.kartollika.myapplication.card.content.factory.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.content.factory.ContentFactory
import com.kartollika.myapplication.databinding.HeaderAsContentLayoutBinding

internal class HeaderAsContentFactory : ContentFactory<ContentType.Header> {

  private lateinit var binding: HeaderAsContentLayoutBinding

  override fun provideView(context: Context): View {
    return HeaderAsContentLayoutBinding.inflate(LayoutInflater.from(context)).apply {
      binding = this
    }.root
  }

  override fun applyData(model: ContentType.Header) {
//    binding.image = model.image
    binding.header.text = model.header
  }
}