package ru.easycode.blogpostsintensive.presentation

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class DeleteImageCustomButton: androidx.appcompat.widget.AppCompatImageButton, ChangeVisibility {

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

    override fun hide() {
        visibility = GONE
    }

    override fun show() {
        visibility = VISIBLE
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val state = VisibilitySavedState(it)
            state.save(this)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as VisibilitySavedState?
        super.onRestoreInstanceState(restoredState?.superState)
        restoredState?.restore(this)
    }

}

private class VisibilitySavedState: View.BaseSavedState {

    private var visibility: Int = View.VISIBLE

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        visibility = parcelIn.readInt()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(visibility)
    }
    fun save(view: View) {
        this.visibility = view.visibility
    }

    fun restore(view: View) {
        view.visibility = this.visibility
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VisibilitySavedState> {
        override fun createFromParcel(parcel: Parcel): VisibilitySavedState =
            VisibilitySavedState(parcel)

        override fun newArray(size: Int): Array<VisibilitySavedState?> =
            arrayOfNulls(size)
    }
}