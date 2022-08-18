package app.coinfo.feature.search.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.presentation.home.adapter.TrendingCoinUI
import app.coinfo.feature.search.presentation.home.adapter.TrendingCoinsAdapter
import com.bumptech.glide.Glide


@BindingAdapter("trendingCoins")
fun bindTrendingCoins(recyclerView: RecyclerView, coins: List<TrendingCoinUI>) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.submitList(coins)
}

@BindingAdapter("glideImage")
fun bingImage(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): TrendingCoinsAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is TrendingCoinsAdapter) {
        recyclerView.adapter as TrendingCoinsAdapter
    } else {
        val bindableRecyclerAdapter = TrendingCoinsAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}