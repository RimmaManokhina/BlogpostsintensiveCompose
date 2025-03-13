package ru.easycode.blogpostsintensive.domain

interface BlogPost {

    interface Mapper<T : Any> {

        fun toBase(
            id: String,
            ownerId: String,
            isMyPost: Boolean,
            postOwnerName: String,
            originalPostId: String,
            text: String,
            createdAt: Long,
            editedAt: Long,
            imageLink: String
        ): T

        fun toExpanded(base: BlogPost, statistics: Statistics): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    fun id(): String

    fun message(): String = ""

    fun ownerId(): String

    fun createdAt(): Long

    fun editedAt(): Long

    fun originalPostId(): String = ""

    fun isMyPost(): Boolean

    fun imageLink(): String = ""

    data class Base(
        private val id: String,
        private val ownerId: String,
        private val isMyPost: Boolean = false,
        private val postOwnerName: String = "",
        private val originalPostId: String = "",
        private val text: String,
        private val createdAt: Long = 0L,
        private val editedAt: Long = 0L,
        private val imageLink: String = ""
    ) : BlogPost {

        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.toBase(
                id,
                ownerId,
                isMyPost,
                postOwnerName,
                originalPostId,
                text,
                createdAt,
                editedAt,
                imageLink
            )

        override fun id() = id

        override fun message() = text

        override fun ownerId() = ownerId

        override fun createdAt() = createdAt

        override fun editedAt() = editedAt

        override fun originalPostId() = originalPostId

        override fun isMyPost() = isMyPost

        override fun imageLink() = imageLink

    }

    data class Statistics(
        val likesCount: Int,
        val isLiked: Boolean,
        val repostsCount: Int,
        val isReposted: Boolean,
    )

    data class Combo(
        private val base: BlogPost,
        private val statistics: Statistics
    ) : BlogPost {

        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.toExpanded(base = base, statistics = statistics)

        override fun id() = base.id()

        override fun ownerId() = base.ownerId()

        override fun createdAt() = base.createdAt()

        override fun editedAt() = base.editedAt()

        override fun isMyPost() = base.isMyPost()

        override fun imageLink() = base.imageLink()
    }
}