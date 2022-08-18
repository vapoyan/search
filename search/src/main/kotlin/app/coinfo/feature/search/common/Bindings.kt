package app.coinfo.feature.search.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.presentation.feature.home.adapter.TrendingCoinsAdapter
import app.coinfo.feature.search.presentation.model.TrendingCoinUI
import com.bumptech.glide.Glide

object Bindings {

    @JvmStatic
    @BindingAdapter("trendingCoins")
    fun bindTrendingCoins(recyclerView: RecyclerView, coins: List<TrendingCoinUI>) {
        val adapter = getOrCreateAdapter(recyclerView)
        adapter.submitList(coins)
    }

    @JvmStatic
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
}