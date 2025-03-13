package ru.easycode.blogpostsintensive.core

import android.content.Context
import android.net.Uri
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.FileContent
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.api.services.drive.model.Permission
import ru.easycode.blogpostsintensive.R

interface GoogleDriveService {

    suspend fun driveService(myUserEmail: String): Drive

    suspend fun uploadFileIdToGoogleDrive(uri: Uri, myUserEmail: String): String

    suspend fun deleteImageFromGoogleDrive(fileId: String,myUserEmail: String)

    class Base(
        private val context: Context,
        private val mediaStorageFileInfo: MediaStorageFileInfo,
    ) : GoogleDriveService {

        override suspend fun driveService(myUserEmail: String): Drive {
            val credential =
                GoogleAccountCredential.usingOAuth2(context, listOf(DriveScopes.DRIVE_FILE))
            credential.setSelectedAccountName(myUserEmail)
            return Drive.Builder(
                AndroidHttp.newCompatibleTransport(),
                JacksonFactory.getDefaultInstance(),
                credential
            )
                .setApplicationName(context.getString(R.string.app_name))
                .build()
        }

        override suspend fun uploadFileIdToGoogleDrive(
            uri: Uri,
            myUserEmail: String,
        ): String {
            return try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val filePath =
                    mediaStorageFileInfo.fileFromInputStream(inputStream, context.cacheDir)
                val loadFile = com.google.api.services.drive.model.File().apply {
                    name = mediaStorageFileInfo.fileNameFromMediaStorage(uri)
                }
                val compressedImageFile = mediaStorageFileInfo.compressImage(filePath.path)
                val mediaContent = FileContent("image/jpeg", compressedImageFile)

                val uploadFile =
                    driveService(myUserEmail).files().create(loadFile, mediaContent)
                        .setFields("id, webContentLink")
                        .execute()
                val fileId = uploadFile.id

                val userPermission = Permission()
                    .setType("anyone")
                    .setRole("reader")
                driveService(myUserEmail).permissions().create(fileId, userPermission)
                    .setFields("id")
                    .execute()

                uploadFile.webContentLink

            } catch (e: GoogleJsonResponseException) {
                throw RuntimeException("Unable to upload file: ${e.message}", e)
            }
        }

        override suspend fun deleteImageFromGoogleDrive(fileId: String, myUserEmail: String) {
            driveService(myUserEmail).files().delete(fileId)
        }
    }

}