package ru.easycode.blogpostsintensive.presentation

import androidx.recyclerview.widget.DiffUtil

class CommonDiffUtil(
    private val oldList: List<ListItemUi>,
    private val newList: List<ListItemUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id() == newList[newItemPosition].id()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}