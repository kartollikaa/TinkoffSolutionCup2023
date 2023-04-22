package com.kartollika.myapplication.list.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class SpacingItemDecoration(
  private val spacing: Int,
  private val orientation: Orientation = Orientation.VERTICAL,
) : ItemDecoration() {

  enum class Orientation {
    VERTICAL,
    HORIZONTAL,
    ;
  }

  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    super.getItemOffsets(outRect, view, parent, state)

    if (orientation == Orientation.VERTICAL) {
      if (!isFirstChild(parent, view)) {
        outRect.top = spacing
      }
      if (!isLastChild(parent, view)) {
        outRect.bottom = spacing
      }
    } else {
      if (!isFirstChild(parent, view)) {
        outRect.left = spacing
      }

      if (!isLastChild(parent, view)) {
        outRect.right = spacing
      }
    }
  }

  private fun isLastChild(parent: RecyclerView, view: View): Boolean {
    return parent.getChildAdapterPosition(view) == parent.childCount - 1
  }

  private fun isFirstChild(
    parent: RecyclerView,
    view: View
  ) = parent.getChildAdapterPosition(view) == 0
}