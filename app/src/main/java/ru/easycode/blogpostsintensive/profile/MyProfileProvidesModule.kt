package ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.easycode.blogpostsintensive.domain.BlogPost
import ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation.BaseBlogPostMapper
import ru.easycode.blogpostsintensive.profile.other.ru.easycode.blogpostsintensive.profile.presentation.BlogPostUi
import java.util.Locale

/**
 * @author DaisyBell on 14.03.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object MyProfileProvidesModule {

    @Provides
    fun provideMaxPosts(): Int = 100

    @Provides
    fun provideDatePattern(): String = "HH:mm dd MMMM yyyy"

    @Provides
    fun provideLocale(@ApplicationContext context: Context): Locale =
        context.resources.configuration.locales[0]

    @Provides
    fun provideBlogPostMapper(): BlogPost.Mapper<BlogPostUi> = BaseBlogPostMapper()
}