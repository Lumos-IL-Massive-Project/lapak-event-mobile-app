package com.example.connectus.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewRowGapItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: android.view.View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        outRect.right = space
        outRect.left = space

        when (position) {
            0 -> {
                outRect.left = 0
                outRect.right = space
            }
            itemCount - 1 -> {
                outRect.left = space
                outRect.right = 0
            }
        }
    }
}