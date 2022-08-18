package app.coinfo.feature.search.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.databinding.SearchItemTrendingCoinsBinding
import com.bumptech.glide.Glide

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
            with(binding) {
                textViewTrendingCoinSymbol.text = coin.symbol
                Glide.with(imageViewTrendingCoinLogo).load(coin.thumbnailUrl)
                    .into(imageViewTrendingCoinLogo)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TrendingCoinUI>() {
        override fun areItemsTheSame(oldItem: TrendingCoinUI, newItem: TrendingCoinUI) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TrendingCoinUI, newItem: TrendingCoinUI) =
            oldItem == newItem
    }
}