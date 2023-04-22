package com.kartollika.myapplication.util

import android.view.View
import androidx.core.view.isVisible

fun View.visible() {
  isVisible = true
}

fun View.gone() {
  isVisible = false
}