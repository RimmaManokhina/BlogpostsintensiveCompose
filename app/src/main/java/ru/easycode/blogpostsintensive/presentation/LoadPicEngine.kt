package ru.easycode.blogpostsintensive.presentation

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface LoadPicEngine {

    fun show(imageView: ImageView, imageUrl: String)

    class Base : LoadPicEngine {

        override fun show(imageView: ImageView, imageUrl: String) {
            Picasso.get().load(imageUrl)
                .into(imageView)
        }
    }

}