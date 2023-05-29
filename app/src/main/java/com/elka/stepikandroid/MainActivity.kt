package com.elka.stepikandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elka.stepikandroid.databinding.ActivityMainBinding
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private var request: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.text.setOnClickListener {


      val o = createRequest(NEWS_URL).map { Gson().fromJson(it, Feed::class.java) }
      request = o.subscribe({ result ->
        for (item in result.items) {
          Log.d("FEED_LOADS", item.toString())
        }
      }, { error -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show() })
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    request?.dispose()
  }
}

data class Feed(
  val items: ArrayList<FeedItem>
)

data class FeedItem(
  val title: String,
  val link: String,
  val thumbnail: String,
  val description: String,
) {
  override fun toString() = "$title $description $thumbnail $link"
}