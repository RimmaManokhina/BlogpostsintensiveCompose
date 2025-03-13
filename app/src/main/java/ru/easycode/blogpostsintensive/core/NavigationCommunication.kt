package ru.easycode.blogpostsintensive.core

interface NavigationCommunication {

    interface Observe : Communication.Observe<Screen>

    interface Update : Communication.Update<Screen>

    interface Mutable : Observe, Update

    class Base : Communication.Single<Screen>(), Mutable
}
