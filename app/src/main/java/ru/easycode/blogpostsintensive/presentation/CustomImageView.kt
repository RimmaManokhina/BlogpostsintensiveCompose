package ru.easycode.blogpostsintensive.presentation

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import ru.easycode.blogpostsintensive.common.ProvideLoadPicEngine

class CustomImageView : androidx.appcompat.widget.AppCompatImageView, ShowPicture {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        visibility = GONE
    }

    private val picEngine: LoadPicEngine by lazy {
        (context.applicationContext as ProvideLoadPicEngine).picEngine()
    }

    private var imageUrl: String = ""

    override fun show(url: String) {
        if (url.isNotEmpty()) {
            visibility = View.VISIBLE
            picEngine.show(this, url)
            this.imageUrl = url
        } else {
            visibility = View.GONE
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val state = CustomImageViewSavedState(it)
            state.save(this, imageUrl)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as CustomImageViewSavedState?
        super.onRestoreInstanceState(restoredState?.superState)
        show(restoredState?.restore(this)?.first ?: "")
    }

}

internal class CustomImageViewSavedState : View.BaseSavedState {

    private var imageUrl = ""
    private var visibility: Int = View.VISIBLE
    private var uri: Uri = Uri.EMPTY

    fun save(view: View, url: String, uri: Uri = Uri.EMPTY) {
        this.visibility = view.visibility
        imageUrl = url
        this.uri = uri
    }

    fun restore(view: View): Pair<String, Uri> {
        view.visibility = this.visibility
        return Pair(imageUrl, uri)
    }

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        visibility = parcelIn.readInt()
        imageUrl = parcelIn.readString() ?: ""
        uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readParcelable(Uri::class.java.classLoader, Uri::class.java) ?: Uri.EMPTY
        } else
            parcelIn.readParcelable(Uri::class.java.classLoader) ?: Uri.EMPTY
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(visibility)
        out.writeString(imageUrl)
        out.writeParcelable(uri, flags)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<CustomImageViewSavedState> {
        override fun createFromParcel(parcel: Parcel): CustomImageViewSavedState =
            CustomImageViewSavedState(parcel)

        override fun newArray(size: Int): Array<CustomImageViewSavedState?> =
            arrayOfNulls(size)
    }
}

interface ShowPicture {
    fun show(url: String)
}
