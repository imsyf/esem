package net.testportal.suitmedia.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("urlToImage")
fun ImageView.bindImage(urlToImage: String?) {
    if (urlToImage != null) {
        Glide.with(context).load(urlToImage).into(this)
    }
}
