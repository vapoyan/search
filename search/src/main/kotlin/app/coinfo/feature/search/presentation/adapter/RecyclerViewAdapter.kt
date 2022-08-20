package app.coinfo.feature.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

internal class RecyclerViewAdapter(
    @LayoutRes val layoutId: Int
) : ListAdapter<RecyclerViewItem, RecyclerViewHolder>(DiffCallback()) {

    private var dataClickListener: RecyclerViewItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    ) { position ->
        dataClickListener?.onItemClicked(currentList[position])
    }

    fun setDataClickListener(listener: RecyclerViewItemClickListener) {
        dataClickListener = listener
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<RecyclerViewItem>() {
        override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RecyclerViewItem,
            newItem: RecyclerViewItem
        ) =
            oldItem.id == newItem.id
    }
}

