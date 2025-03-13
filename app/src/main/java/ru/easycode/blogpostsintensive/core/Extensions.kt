package ru.easycode.blogpostsintensive.core

import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


inline fun <reified T : Serializable> Parcel.readSerializableWithDiffVersions(): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.readSerializable(T::class.java.classLoader, T::class.java)!!
    } else {
        this.readSerializable() as T
    }

inline fun <reified T : Serializable> Bundle.getSerializableWithDiffVersions(key: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializable(key, T::class.java)!!
    } else {
        this.getSerializable(key) as T
    }

inline fun <reified T : Parcelable> Bundle.getParcelableArrayListWithDiffVersions(key: String): ArrayList<T> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelableArrayList(key, T::class.java)!!
    } else {
        this.getParcelableArrayList<T>(key) as ArrayList<T>
    }
