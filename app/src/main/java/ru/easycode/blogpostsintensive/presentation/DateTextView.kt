package ru.easycode.blogpostsintensive.presentation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.core.DateConverter

class DateTextView : MaterialTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val dateConverter: DateConverter

    init {
        freezesText = true
        dateConverter = DateConverter.Base(locale = context.resources.configuration.locales[0])
    }

    fun showDate(createdAt: Long, editedAt: Long) {
        text = if (editedAt == 0L) {
            dateConverter.convertToLocal(createdAt)
        } else {
            "${context.getString(R.string.edited)} ${dateConverter.convertToLocal(editedAt)}"
        }
    }
}
