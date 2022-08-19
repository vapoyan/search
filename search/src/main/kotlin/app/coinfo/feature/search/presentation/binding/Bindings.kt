package app.coinfo.feature.search.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.common.widget.recyclerview.BindableRecyclerData
import app.coinfo.feature.search.common.widget.recyclerview.BindableRecyclerViewAdapter
import app.coinfo.feature.search.common.widget.recyclerview.OnDataClicked
import com.bumptech.glide.Glide


internal object Bindings {

    @JvmStatic
    @BindingAdapter(value = ["bind:onRecyclerViewItemClicked"])
    internal fun bindRecyclerViewDataClickListener(
        view: RecyclerView,
        onCoinClicked: OnDataClicked
    ) {
        (view.adapter as BindableRecyclerViewAdapter).setDataClickListener(onCoinClicked)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:data"])
    internal fun bindRecyclerViewData(view: RecyclerView, data: BindableRecyclerData) {
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
    ): BindableRecyclerViewAdapter {
        return if (recyclerView.adapter != null && recyclerView.adapter is BindableRecyclerViewAdapter) {
            recyclerView.adapter as BindableRecyclerViewAdapter
        } else {
            val bindableRecyclerAdapter = BindableRecyclerViewAdapter(id)
            recyclerView.adapter = bindableRecyclerAdapter
            bindableRecyclerAdapter
        }
    }

}
