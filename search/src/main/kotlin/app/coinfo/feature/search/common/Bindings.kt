package app.coinfo.feature.search.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.presentation.feature.home.adapter.TrendingCoinsAdapter
import app.coinfo.feature.search.presentation.model.TrendingCoinUI
import com.bumptech.glide.Glide

internal object Bindings {

    @JvmStatic
    @BindingAdapter("trendingCoins")
    fun bindTrendingCoins(recyclerView: RecyclerView, coins: List<TrendingCoinUI>) {
        val adapter = getOrCreateAdapter(recyclerView)
        adapter.submitList(coins)
    }

    /** Loads the image for the given [url] and sets into the [view] ([ImageView])*/
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageFromUrl(view: ImageView, url: String?) {
        // TODO: Set the placeholder image, so it is displayed in the case of error.
        Glide.with(view).load(url).into(view)
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