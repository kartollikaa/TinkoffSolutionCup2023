package com.kartollika.myapplication.card.content.factory

import android.content.Context
import android.view.View
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.content.factory.impl.CellFactory
import com.kartollika.myapplication.card.content.factory.impl.HeaderAsContentFactory
import com.kartollika.myapplication.card.content.factory.impl.HeaderSubheaderFactory
import com.kartollika.myapplication.card.content.factory.impl.ScrollCardsFactory
import com.kartollika.myapplication.card.content.factory.impl.VerticalListFactory

/**
 * Базовый интерфейс фабрики для создания контента и применения к нему данных
 */
interface ContentFactory<in T> where T : ContentType {
  fun provideView(context: Context): View
  fun applyData(model: T)
}

@Suppress("UNCHECKED_CAST")
fun getContentFactoryByType(contentType: ContentType): ContentFactory<ContentType> {
  return when (contentType) {
    is ContentType.Header -> HeaderAsContentFactory()
    is ContentType.HeaderSubheader -> HeaderSubheaderFactory()
    is ContentType.HorizontalScrollingCards -> ScrollCardsFactory()
    is ContentType.VerticalList -> VerticalListFactory()
    is ContentType.Cell -> CellFactory()
  } as ContentFactory<ContentType>
}