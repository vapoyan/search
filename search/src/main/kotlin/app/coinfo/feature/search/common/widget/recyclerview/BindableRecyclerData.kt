package app.coinfo.feature.search.common.widget.recyclerview

import androidx.annotation.LayoutRes

internal data class BindableRecyclerData(
    @LayoutRes val layoutId: Int,
    val data: List<RecyclerViewDataItem>
)
