package com.kartollika.myapplication.util

import android.content.res.Resources
import android.util.DisplayMetrics
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToLong

object Screen {

  fun pxToDp(px: Int): Long {
    val metrics = Resources.getSystem().displayMetrics
    return (px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToLong()
  }

  fun dpToPx(dp: Int): Int = dp(dp)

  fun dp(dp: Int): Int = dp(dp.toFloat())

  fun dp(dp: Float): Int = dpFloat(dp).toInt()

  fun dpFloat(dp: Int): Float = dpFloat(dp.toFloat())

  fun dpFloat(dp: Float): Float = Math.floor((dp * density()).toDouble()).toFloat()

  fun density(): Float = getDisplayMetrics().density

  private fun getDisplayMetrics(): DisplayMetrics {
    return Resources.getSystem().displayMetrics
  }

  fun width() = min(
    getDisplayMetrics().widthPixels, getDisplayMetrics().heightPixels
  )

  fun height() = max(
    getDisplayMetrics().widthPixels, getDisplayMetrics().heightPixels
  )
}