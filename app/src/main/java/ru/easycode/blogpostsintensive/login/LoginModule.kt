package ru.easycode.blogpostsintensive.login

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.easycode.blogpostsintensive.cloudservice.Auth
import ru.easycode.blogpostsintensive.cloudservice.MyUser
import ru.easycode.blogpostsintensive.cloudservice.Service
import ru.easycode.blogpostsintensive.core.ManageResource
import ru.easycode.blogpostsintensive.core.RunAsync
import ru.easycode.blogpostsintensive.login.data.LoginCloudDataSource
import ru.easycode.blogpostsintensive.login.data.LoginRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule() {

    @Binds
    abstract fun bindsAuth(
        auth: Auth.Base
    ): Auth

    @Binds
    abstract fun bindsMyUser(
        myUser: MyUser.Base
    ): MyUser

    @Binds
    abstract fun bindsLoginCloudDataSource(
        loginCloudDataSource: LoginCloudDataSource.Base
    ): LoginCloudDataSource

    @Binds
    abstract fun bindsManageResource(
        manageResource: ManageResource.Base
    ): ManageResource

    @Binds
    abstract fun bindsRunAsync(
        runAsync: RunAsync.Base
    ): RunAsync

    @Binds
    abstract fun bindsLoginRepository(
        loginRepository: LoginRepository.Base
    ): LoginRepository

    @Binds
    abstract fun bindsService(
        service: Service.Base
    ): Service
}