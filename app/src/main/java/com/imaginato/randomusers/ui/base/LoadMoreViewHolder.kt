package com.imaginato.randomusers.ui.base

import com.imaginato.randomusers.databinding.ItemRecyclerLoaderBinding

class LoadMoreViewHolder<T>(
    private val binding: ItemRecyclerLoaderBinding
) : BaseRecyclerViewHolder<T>(binding.root) {
    override fun bind(item: T, position: Int) {
    }
}
