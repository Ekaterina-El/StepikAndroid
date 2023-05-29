package com.elka.stepikandroid

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elka.stepikandroid.databinding.FeedItemBinding

class FeedAdapter(private val listener: FeedViewHolder.Companion.Listener): RecyclerView.Adapter<FeedViewHolder>() {
  private val lists = mutableListOf<FeedItem>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
    val binding = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return FeedViewHolder(binding, listener)
  }

  override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
    holder.bind(lists[position])
  }

  override fun getItemCount() = lists.size

  @SuppressLint("NotifyDataSetChanged")
  fun setItems(items: ArrayList<FeedItem>) {
    lists.clear()
    lists.addAll(items)
    notifyDataSetChanged()
  }
}