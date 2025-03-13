package ru.easycode.blogpostsintensive.core

import androidx.lifecycle.ViewModel

interface DependencyContainer {

    fun module(className: Class<out ViewModel>): Module<out ViewModel>

    class Error : DependencyContainer {

        override fun module(className: Class<out ViewModel>): Module<out ViewModel> =
            throw IllegalArgumentException("unknown className $className")
    }
}