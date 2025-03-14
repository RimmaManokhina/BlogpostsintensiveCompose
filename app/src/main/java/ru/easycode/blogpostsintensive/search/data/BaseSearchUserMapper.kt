package ru.easycode.blogpostsintensive.search.data

import ru.easycode.blogpostsintensive.search.SearchUserCard
import ru.easycode.blogpostsintensive.search.presentation.SearchUserModel
import javax.inject.Inject

class BaseSearchUserMapper @Inject constructor() : SearchUserModel.Mapper<SearchUserCard> {

    override fun map(id: String, name: String, email: String) =
        SearchUserCard.Base(id = id, text = "$name\n$email")
}