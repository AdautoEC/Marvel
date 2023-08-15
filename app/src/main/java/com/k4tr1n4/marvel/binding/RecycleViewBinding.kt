package com.k4tr1n4.marvel.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k4tr1n4.marvel.ui.main.MainViewModel
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.whatif.whatIfNotNullAs

object RecycleViewBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }
    }

    @JvmStatic
    @BindingAdapter("paginationPokemonList")
    fun paginationList(view: RecyclerView, viewModel: MainViewModel) {
        RecyclerViewPaginator(
            recyclerView = view,
            isLoading = { viewModel.isLoading },
            loadMore = { viewModel.fetchNextMarvelList() },
            onLast = { false }
        ).run {
            threshold = 8
        }
    }
}