package com.example.musicsearchengine.utils

import android.content.Intent
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.example.musicsearchengine.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
            .load(imageUrl?.replace("100x100", "300x300"))
            .placeholder(android.R.color.background_light)
            .into(view)
}

@BindingAdapter("imageUrlWithTransition", "fragment")
fun loadImageWithPostponedTransition(imageView: ImageView, imageUrl: String?, fragment: Fragment) {
    Picasso.get()
        .load(imageUrl?.replace("100x100", "400x400"))
        .into(imageView, object: Callback {
            override fun onSuccess() {
                fragment.startPostponedEnterTransition()

            }
            override fun onError(e: java.lang.Exception?) {
                fragment.startPostponedEnterTransition()
            }
        })

}

@BindingAdapter("albumYear")
fun setAlbumYear(view: TextView, date: String?) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    date?.let {
        val dateTime = dateFormat.parse(it)
        val cal = Calendar.getInstance()
        cal.time = dateTime
        val year = cal[Calendar.YEAR]
        view.text = year.toString()
    }
}

@BindingAdapter("songDuration")
fun setSongDuration(textView: TextView, duration: Long) {
    val context = textView.context
    val minutes = duration / 1000 / 60
    val seconds = duration / 1000 % 60
    textView.text = context.getString(R.string.song_duration,minutes, seconds)
}

@BindingAdapter("songUrl")
fun setShareIntent(imageView: ImageView, songUrl: String?) {
    val context = imageView.context
    imageView.setOnClickListener {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, songUrl)
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.send_to)))
    }
}

@BindingAdapter("favorite")
fun setFavorite(imageView: ImageView, isFavorite: Boolean) {
    val color = if (isFavorite) {
        ContextCompat.getColor(imageView.context, R.color.colorAccent)
    } else {
        ContextCompat.getColor(imageView.context, R.color.icon_disabled)
    }
    ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color))
}