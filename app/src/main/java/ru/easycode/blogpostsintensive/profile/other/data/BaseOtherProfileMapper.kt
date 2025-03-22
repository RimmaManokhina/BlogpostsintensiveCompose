package ru.easycode.blogpostsintensive.profile.other.data

import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.other.presentation.BlogPostCard
import javax.inject.Inject

class BaseOtherProfileMapper @Inject constructor() : BlogPost.Mapper<BlogPostCard> {

    override fun toBase(
        id: String,
        ownerId: String,
        text: String
    ): BlogPostCard {
        return BlogPostCard.Base(
            id = id,
            message = text
        )
    }
}