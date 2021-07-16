package com.imaginato.randomusers.ui.map

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.imaginato.randomusers.R
import com.imaginato.randomusers.data.randomuser.entity.RandomUserItem
import com.imaginato.randomusers.databinding.ItemRecyclerLoaderBinding
import com.imaginato.randomusers.databinding.ItemUserListBinding
import com.imaginato.randomusers.ui.base.BaseRecyclerViewAdapter
import com.imaginato.randomusers.ui.base.BaseRecyclerViewHolder
import com.imaginato.randomusers.ui.base.LoadMoreViewHolder
import java.util.*

/**
 * Random user adapter
 * @param itemClick itemClick Listener
 */
class RandomUserAdapter(private val itemClick: (user: RandomUserItem) -> Unit) :
    BaseRecyclerViewAdapter<RandomUserItem, BaseRecyclerViewHolder<RandomUserItem>>() {
    companion object {
        private const val ITEM = 0
        private const val LOADING = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mItems.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun createItemViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<RandomUserItem> {
        return if (viewType == LOADING) {
            LoadMoreViewHolder(
                ItemRecyclerLoaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            RandomUserViewHolder(
                ItemUserListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun bindItemViewHolder(holder: BaseRecyclerViewHolder<RandomUserItem>, position: Int) {
        holder.bind(mItems[position], position)
    }

    fun setData(it: ArrayList<RandomUserItem>) {
        addAll(it, true)
    }

    /**
     * ViewHolder for binding user data
     */
    inner class RandomUserViewHolder(
        private val binding: ItemUserListBinding
    ) : BaseRecyclerViewHolder<RandomUserItem>(binding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: RandomUserItem, position: Int) {
            with(item) {
                binding.tvName.text = "${name?.title} ${name?.first} ${name?.last}"
                binding.tvCity.text = location?.city

                Glide.with(binding.ivUserThumb)
                    .load(picture?.thumbnail)
                    .transform(
                        RoundedCorners(
                            binding.ivUserThumb.context.resources.getDimensionPixelSize(
                                R.dimen._28sdp
                            )
                        )
                    )
                    .into(binding.ivUserThumb)

                binding.root.setOnClickListener {
                    itemClick.invoke(this)
                }
            }
        }
    }
}