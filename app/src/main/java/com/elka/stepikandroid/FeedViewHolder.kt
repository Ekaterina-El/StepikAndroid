package com.elka.stepikandroid

import androidx.recyclerview.widget.RecyclerView
import com.elka.stepikandroid.databinding.FeedItemBinding

class FeedViewHolder(private val binding: FeedItemBinding, private val listener: Listener) : RecyclerView.ViewHolder(binding.root) {
  fun bind(feedItem: FeedItem) {
    binding.feedItem = feedItem
    binding.root.setOnClickListener { listener.onSelect(feedItem) }
  }

  companion object {
    interface Listener {
      fun onSelect(feedItem: FeedItem)
    }
  }
}