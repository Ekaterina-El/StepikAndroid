package com.elka.stepikandroid

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