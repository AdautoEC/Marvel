package com.k4tr1n4.marvel.ui.main

import android.view.ViewGroup
import com.k4tr1n4.marvel.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.k4tr1n4.core.model.Character
import com.k4tr1n4.marvel.databinding.ItemMarvelBinding
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class MarvelAdapter : BindingListAdapter<Character, MarvelAdapter.MarvelViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) =
        holder.bindMarvel(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder =
        parent.binding<ItemMarvelBinding>(R.layout.item_marvel).let(::MarvelViewHolder)

    inner class MarvelViewHolder constructor(
        private val binding: ItemMarvelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {

        }

        fun bindMarvel(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}