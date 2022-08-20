package app.coinfo.feature.search.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.presentation.adapter.RecyclerViewAdapter
import app.coinfo.feature.search.presentation.adapter.RecyclerViewData
import app.coinfo.feature.search.presentation.adapter.RecyclerViewItemClickListener
import com.bumptech.glide.Glide


internal object Bindings {

    @JvmStatic
    @BindingAdapter(value = ["bind:onRecyclerViewItemClicked"])
    internal fun bindRecyclerViewDataClickListener(
        view: RecyclerView,
        onCoinClicked: RecyclerViewItemClickListener
    ) {
        (view.adapter as RecyclerViewAdapter).setDataClickListener(onCoinClicked)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:data"])
    internal fun bindRecyclerViewData(view: RecyclerView, data: RecyclerViewData) {
        val adapter = getOrCreateAdapter(view, data.layoutId)
        adapter.submitList(data.data)
    }


    /** Loads the image for the given [url] and sets into the [view] ([ImageView])*/
    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    internal fun setImageFromUrl(view: ImageView, url: String?) {
        // TODO: Set the placeholder image, so it is displayed in the case of error.
        Glide.with(view).load(url).into(view)
    }

    private fun getOrCreateAdapter(
        recyclerView: RecyclerView,
        id: Int
    ): RecyclerViewAdapter {
        return if (recyclerView.adapter != null && recyclerView.adapter is RecyclerViewAdapter) {
            recyclerView.adapter as RecyclerViewAdapter
        } else {
            val bindableRecyclerAdapter = RecyclerViewAdapter(id)
            recyclerView.adapter = bindableRecyclerAdapter
            bindableRecyclerAdapter
        }
    }

}
