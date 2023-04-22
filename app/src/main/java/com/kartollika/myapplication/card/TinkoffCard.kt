package com.kartollika.myapplication.card

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.kartollika.myapplication.R
import com.kartollika.myapplication.card.content.ContentType
import com.kartollika.myapplication.card.content.factory.getContentFactoryByType
import com.kartollika.myapplication.card.footer.Footer
import com.kartollika.myapplication.card.header.Header
import com.kartollika.myapplication.databinding.TinkoffCardFooterButtonBinding
import com.kartollika.myapplication.databinding.TinkoffCardHeaderBinding
import com.kartollika.myapplication.databinding.TinkoffCardSkeletonBinding
import com.kartollika.myapplication.util.gone
import com.kartollika.myapplication.util.visible


class TinkoffCard @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

  private val binding: TinkoffCardSkeletonBinding
  private var footerBinding: TinkoffCardFooterButtonBinding? = null
  private var headerBinding: TinkoffCardHeaderBinding? = null
  private var footer: Footer? = null
  private var header: Header? = null

  init {
    binding = TinkoffCardSkeletonBinding.inflate(LayoutInflater.from(context), this, true)

    val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TinkoffCard)
    val flat = a.getBoolean(R.styleable.TinkoffCard_flat, false)
    a.recycle()

    setFlat(flat)
  }

  fun setFlat(flat: Boolean) {
    if (flat) {
      binding.root.cardElevation = 0f
      val color = ContextCompat.getColor(context, R.color.cardBackgroundFlat)
      binding.root.setCardBackgroundColor(ColorStateList.valueOf(color))
    } else {
      binding.root.cardElevation = 8f
      val color = ContextCompat.getColor(context, R.color.cardBackground)
      binding.root.setCardBackgroundColor(ColorStateList.valueOf(color))
    }
    invalidate()
  }

  fun setContent(contentType: ContentType) {
    // TODO проверять, что тот же contentType.
    //  Если другой, то очищать вьюшки. Если нет, то заменить существующую
    binding.cardContentSlot.removeAllViews()

    val factory = getContentFactoryByType(contentType)

    binding.cardContentSlot.addView(
      factory.provideView(context),
      LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    )

    factory.applyData(contentType)
  }

  // Set footer or remove it if passed null
  fun setHeader(header: Header?) {
    if (header == null) {
      binding.cardHeaderSlot.removeAllViews()
      binding.cardHeaderSlot.gone()
      this.header = null
    } else {
      if (this.header == null) {
        addHeader()
        binding.cardHeaderSlot.visible()
      }

      setHeaderData(header)
      this.header = header
    }
  }

  private fun addHeader() {
    val headerBinding = TinkoffCardHeaderBinding.inflate(
      LayoutInflater.from(context),
      binding.cardHeaderSlot,
      true
    )
    this.headerBinding = headerBinding
  }

  // Set footer or remove it if passed null
  fun setFooter(footer: Footer?) {
    if (footer == null) {
      binding.cardFooterSlot.removeAllViews()
      binding.cardFooterSlot.gone()
      this.footer = null
    } else {
      if (this.footer == null) {
        addFooter()
        binding.cardFooterSlot.visible()
      }

      setFooterData(footer)
      this.footer = footer
    }
  }

  private fun addFooter() {
    val footerBinding = TinkoffCardFooterButtonBinding.inflate(
      LayoutInflater.from(context),
      binding.cardFooterSlot,
      true
    )
    this.footerBinding = footerBinding
  }

  private fun setFooterData(footer: Footer) {
    footerBinding?.root?.text = footer.title
    footerBinding?.root?.setOnClickListener { footer.onClickListener() }
  }

  private fun setHeaderData(header: Header) {
    val binding = headerBinding ?: return

    binding.header.text = header.header

    when (val action = header.action) {
      is Header.Action.TextButton -> {
        with(binding.button) {
          visible()
          text = action.text
          setOnClickListener { action.action() }
        }
      }
      else -> {
        binding.button.gone()
      }
    }
  }
}