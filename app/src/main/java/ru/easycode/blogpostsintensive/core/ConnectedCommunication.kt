package ru.easycode.blogpostsintensive.core

interface ConnectedCommunication {

    interface Observe : Communication.Observe<Boolean>

    interface Update : Communication.Update<Boolean>

    interface Mutable : Observe, Update

    class Base : Communication.SinglePost<Boolean>(), Mutable
}