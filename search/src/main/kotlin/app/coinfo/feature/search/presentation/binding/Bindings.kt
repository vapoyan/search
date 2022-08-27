package app.coinfo.feature.search.presentation.binding

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.coinfo.feature.search.R
import app.coinfo.feature.search.presentation.adapter.RecyclerViewAdapter
import app.coinfo.feature.search.presentation.adapter.RecyclerViewData
import app.coinfo.feature.search.presentation.adapter.RecyclerViewItemClickListener
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView


internal object Bindings {

    internal interface OnQueryTextSubmit {
        fun onQueryTextSubmit(string: String?)
    }

    internal interface OnQueryTextChange {
        fun onQueryTextChange(string: String?)
    }

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

    @JvmStatic
    @BindingAdapter(
        value = ["bind:onQueryTextSubmit", "bind:onQueryTextChange"],
        requireAll = false
    )
    internal fun setOnQueryTextChangeListener(
        searchView: SearchView,
        onQueryTextSubmit: OnQueryTextSubmit,
        onQueryTextChange: OnQueryTextChange
    ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                onQueryTextSubmit.onQueryTextSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                onQueryTextChange.onQueryTextChange(newText)
                return true
            }
        })
    }


    /** Loads the image for the given [url] and sets into the [view] ([ImageView])*/
    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    internal fun setImageFromUrl(view: ImageView, url: String?) {
        // TODO: Set the placeholder image, so it is displayed in the case of error.
        Glide.with(view).load(url).into(view)
    }

    @JvmStatic
    @BindingAdapter("bind:trendColor")
    internal fun setTrendColor(view: MaterialTextView, trend: Boolean) {
        val attrRes = if (trend) {
            com.google.android.material.R.attr.colorPrimaryVariant
        } else com.google.android.material.R.attr.colorError
        view.setTextColor(view.context.getColorAttributeColor(attrRes))
    }

    @JvmStatic
    @BindingAdapter("bind:trendImage")
    internal fun setImageFromResource(view: ImageView, trend: Boolean) {
        view.setImageResource(if (trend) R.drawable.search_ic_up else R.drawable.search_ic_down)
    }

    private fun Context.getColorAttributeColor(@AttrRes attrRes: Int) = TypedValue()
        .apply { theme.resolveAttribute(attrRes, this, true) }
        .data

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
