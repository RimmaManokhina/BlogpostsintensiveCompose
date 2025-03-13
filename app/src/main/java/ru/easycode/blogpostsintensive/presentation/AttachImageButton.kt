package ru.easycode.blogpostsintensive.presentation

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import android.util.AttributeSet
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.common.ProvideLoadPicEngine

class AttachImageButton : androidx.appcompat.widget.AppCompatImageButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val picEngine: LoadPicEngine by lazy {
        (context.applicationContext as ProvideLoadPicEngine).picEngine()
    }

    private var imageLink: String = ""
    private var imageUri: Uri = Uri.EMPTY

    fun show(url: String) {
        this.imageLink = url
        picEngine.show(this, url)
    }

    fun reset() {
        setImageResource(R.drawable.add_image_48)
        imageLink = ""
        imageUri = Uri.EMPTY
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        this.imageUri = uri ?: Uri.EMPTY
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val state = CustomImageViewSavedState(it)
            state.save(this, imageLink, imageUri)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as CustomImageViewSavedState?
        super.onRestoreInstanceState(restoreState?.superState)
        val (url, uri) = restoreState?.restore(this) ?: Pair("", Uri.EMPTY)
        if (url.isNotEmpty()) show(url)
        else if (uri != Uri.EMPTY) setImageURI(uri)
    }
}