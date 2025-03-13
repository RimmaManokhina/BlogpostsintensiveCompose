package ru.easycode.blogpostsintensive.presentation

import android.content.Context
import android.content.Intent

interface ShareListener {

    fun share(shareInfo: ShareInfo)

    class Base(private val context: Context) : ShareListener {

        override fun share(shareInfo: ShareInfo) {
            val sharedIntent = Intent(Intent.ACTION_SEND)
            sharedIntent.type = "text/plain"
            sharedIntent.putExtra(Intent.EXTRA_TEXT, shareInfo.text())
            val chooser = Intent.createChooser(sharedIntent, "Share")
            context.startActivity(chooser)
        }
    }

}


data class ShareInfo(
    private val userName: String,
    private val message: String,
    private val imageLink: String
) {
    fun text() = "$message \n \n $imageLink \n \n $userName"
}