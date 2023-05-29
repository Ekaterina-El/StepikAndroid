package com.elka.stepikandroid

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.HttpURLConnection
import java.net.URL

const val NEWS_URL =
  "https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.bbci.co.uk%2Fnews%2Frss.xml"

fun createRequest(url: String): Observable<String> {
  return Observable.create<String> {
    val urlConnection = URL(url).openConnection() as HttpURLConnection
    try {
      urlConnection.connect()

      if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) {
        it.onError(RuntimeException(urlConnection.responseMessage))
      } else {
        val result = urlConnection.inputStream.bufferedReader().readText()
        it.onNext(result)
      }
    } finally {
      urlConnection.disconnect()
    }
  }.subscribeOn(Schedulers.io())                  // Исполняем в Input-Output потоке
    .observeOn(AndroidSchedulers.mainThread())    // Получаем результат в основном потоке
}

fun getAllNews(onSuccess: (Feed) -> Unit, onError: (Throwable) -> Unit): Disposable {
  val o = createRequest(NEWS_URL).map { Gson().fromJson(it, Feed::class.java) }
  return o.subscribe({ feed ->
    onSuccess(feed)
  }, { error -> onError(error) })
}