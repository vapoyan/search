package app.coinfo.feature.search.presentation.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.databinding.SearchItemTrendingCoinsBinding
import app.coinfo.feature.search.presentation.model.TrendingCoinUI

class TrendingCoinsAdapter :
    ListAdapter<TrendingCoinUI, TrendingCoinsAdapter.TrendingCoinsHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrendingCoinsHolder(
        SearchItemTrendingCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TrendingCoinsHolder, position: Int) {
        holder.bind(coin = getItem(position))
    }

    class TrendingCoinsHolder(
        private val binding: SearchItemTrendingCoinsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: TrendingCoinUI) {
            binding.coin = coin
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TrendingCoinUI>() {
        override fun areItemsTheSame(oldItem: TrendingCoinUI, newItem: TrendingCoinUI) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TrendingCoinUI, newItem: TrendingCoinUI) =
            oldItem == newItem
    }
}