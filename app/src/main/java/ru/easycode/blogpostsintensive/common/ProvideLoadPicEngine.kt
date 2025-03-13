package ru.easycode.blogpostsintensive.common

import ru.easycode.blogpostsintensive.presentation.LoadPicEngine

interface ProvideLoadPicEngine {

    fun picEngine(): LoadPicEngine
}