package com.elka.stepikandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elka.stepikandroid.databinding.ActivityMainBinding
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private var request: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    getNews()
  }

  private fun getNews() {
    getAllNews(onSuccess = { feed ->
      for (item in feed.items) {
        Log.d("FEED_LOADS", item.toString())
      }
    }, onError = { error -> Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show() })
  }

  override fun onDestroy() {
    super.onDestroy()
    request?.dispose()
  }
}