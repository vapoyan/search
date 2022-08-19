package app.coinfo.feature.search.common.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.BR

internal class BindableRecyclerViewAdapter(
    @LayoutRes val layoutId: Int
) : ListAdapter<RecyclerViewDataItem, BindableViewHolder>(DiffCallback()) {

    private var dataClickListener: OnDataClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BindableViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    ) { position ->
        dataClickListener?.onRecyclerViewItemClicked(currentList[position])
    }

    fun setDataClickListener(listener: OnDataClicked) {
        dataClickListener = listener
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<RecyclerViewDataItem>() {
        override fun areItemsTheSame(oldItem: RecyclerViewDataItem, newItem: RecyclerViewDataItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RecyclerViewDataItem,
            newItem: RecyclerViewDataItem
        ) =
            oldItem.id == newItem.id
    }
}

internal class BindableViewHolder(
    private val binding: ViewDataBinding,
    private val clickedAdPosition: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener { clickedAdPosition(bindingAdapterPosition) }
    }

    fun bind(dataItem: RecyclerViewDataItem) {
        binding.setVariable(BR.data, dataItem)
    }
}