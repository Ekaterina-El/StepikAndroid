package com.elka.stepikandroid

data class FeedItem(
  val title: String,
  val link: String,
  val thumbnail: String,
  val description: String,
) {
  override fun toString() = "$title $description $thumbnail $link"
}