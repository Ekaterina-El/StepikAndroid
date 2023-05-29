package com.elka.stepikandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.elka.stepikandroid.databinding.ActivityMainBinding
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private var request: Disposable? = null

  private val feedAdapterListener by lazy {
    object : FeedViewHolder.Companion.Listener {
      override fun onSelect(feedItem: FeedItem) {
      }
    }
  }
  private val feedAdapter by lazy {
    FeedAdapter(feedAdapterListener)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    binding.apply {
      lifecycleOwner = this@MainActivity
      adapter = this@MainActivity.feedAdapter
    }
    setContentView(binding.root)

    binding.list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    getNews()
  }


  private fun getNews() {
    getAllNews(onSuccess = { feed -> feedAdapter.setItems(feed.items) }, onError = { error ->
      Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
    })
  }

  override fun onDestroy() {
    super.onDestroy()
    request?.dispose()
  }
}