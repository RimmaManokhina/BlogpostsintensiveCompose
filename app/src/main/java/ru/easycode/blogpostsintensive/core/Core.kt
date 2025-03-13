package ru.easycode.blogpostsintensive.core

import android.content.Context
import ru.easycode.blogpostsintensive.cloudservice.MyUser
import ru.easycode.blogpostsintensive.cloudservice.Service

interface ProvideNavigation {
    fun navigation(): NavigationCommunication.Mutable
}

interface ProvideConnectedCommunication {
    fun connectedCommunication(): ConnectedCommunication.Observe
}

interface ProvideManageResource {
    fun manageResource(): ManageResource
}

interface ProvideMyUser {
    fun provideMyUser(): MyUser
}

interface ProvideService {
    fun service(): Service
}

interface ProvideRunAsync {
    fun runAsync(): RunAsync
}

interface LocalProviderBase {
    fun localProvider(): ProvideLocal
}

interface ProvideGoogleDriveService {
    fun googleDriveService(): GoogleDriveService
}


interface Core : ProvideNavigation, ProvideManageResource, ProvideMyUser, ProvideService,
    ProvideConnectedCommunication, ProvideRunAsync, LocalProviderBase, ProvideGoogleDriveService {

    class Base(
        private val myUser: MyUser,
        context: Context,
        private val navigation: NavigationCommunication.Mutable
    ) : Core {

        private val connectedCommunication = ConnectedCommunication.Base()

        private val mediaStorageFileInfo = MediaStorageFileInfo.Base(context)

        private val googleDriveService = GoogleDriveService.Base(context, mediaStorageFileInfo)

        init {
            ConnectionAvailable(connectedCommunication, context)
        }

        private val service: Service = Service.Base(context)

        private val manageResource = ManageResource.Base(context)

        private val runAsync = RunAsync.Base()

        private val localProvider: ProvideLocal = ProvideLocal.Base(context)

        override fun navigation(): NavigationCommunication.Mutable = navigation

        override fun manageResource() = manageResource

        override fun provideMyUser() = myUser

        override fun service() = service

        override fun connectedCommunication() = connectedCommunication

        override fun runAsync() = runAsync

        override fun localProvider() = localProvider

        override fun googleDriveService() = googleDriveService
    }
}