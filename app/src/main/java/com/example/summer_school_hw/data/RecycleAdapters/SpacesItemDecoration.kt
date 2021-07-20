package com.example.summer_school_hw.data.RecycleAdapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    private val topBottom: Int = 0,
    private val leftRight: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom=topBottom
        outRect.top = 0
        outRect.right = 72
        outRect.left = 72
    }
}