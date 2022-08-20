package app.coinfo.feature.search.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.BR

internal class RecyclerViewHolder(
    private val binding: ViewDataBinding,
    private val clickedAdPosition: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener { clickedAdPosition(bindingAdapterPosition) }
    }

    fun bind(dataItem: RecyclerViewItem) {
        binding.setVariable(BR.data, dataItem)
    }
}