package ru.easycode.blogpostsintensive.core

import androidx.lifecycle.ViewModel

interface Module<T : ViewModel> {

    fun viewModel(): T
}