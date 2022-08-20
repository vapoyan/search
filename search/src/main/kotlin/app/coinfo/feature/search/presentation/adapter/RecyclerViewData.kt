package app.coinfo.feature.search.presentation.adapter

import androidx.annotation.LayoutRes

internal data class RecyclerViewData(
    @LayoutRes val layoutId: Int,
    val data: List<RecyclerViewItem>
)
