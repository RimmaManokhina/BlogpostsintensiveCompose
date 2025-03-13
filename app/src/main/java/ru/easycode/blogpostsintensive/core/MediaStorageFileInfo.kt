package ru.easycode.blogpostsintensive.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

interface MediaStorageFileInfo {

    fun fileFromInputStream(inputStream: InputStream?, directory: File): File

    fun fileNameFromMediaStorage(uri: Uri): String

    fun compressImage(filePath: String): File

    class Base(private val context: Context) : MediaStorageFileInfo {

        override fun fileFromInputStream(inputStream: InputStream?, directory: File): File {
            val currentTimeMillis = System.currentTimeMillis()
            val file = File(directory, "uploaded_image_$currentTimeMillis.jpg")
            inputStream?.use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            return file
        }

        override fun fileNameFromMediaStorage(uri: Uri): String {
            context.contentResolver.query(
                uri, null, null, null, null
            )?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    return cursor.getString(displayNameIndex) ?: ""
                }
            }
            return ""
        }

        override fun compressImage(filePath: String): File {
            val bitmap = BitmapFactory.decodeFile(filePath)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)
            val compressFile = File(context.cacheDir, "compressed_image.jpg")
            FileOutputStream(compressFile).use { fileOutputStream ->
                outputStream.writeTo(fileOutputStream)
            }
            return compressFile
        }
    }

}