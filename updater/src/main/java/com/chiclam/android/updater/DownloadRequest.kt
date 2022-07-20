package com.chiclam.android.updater

import android.app.DownloadManager
import android.content.Context
import android.net.Uri


class DownloadRequest(fileUrl: String) {

    internal var rawRequest = DownloadManager.Request(Uri.parse(fileUrl))

    fun setTitle(title: CharSequence): DownloadRequest {
        rawRequest.setTitle(title)
        return this
    }

    fun setDescription(description: CharSequence): DownloadRequest {
        rawRequest.setDescription(description)
        return this
    }

    fun allowScanningByMediaScanner(): DownloadRequest {
        rawRequest.allowScanningByMediaScanner()
        return this
    }

    fun setDestinationInExternalFilesDir(
        context: Context,
        dirType: String?,
        subPath: String?
    ): DownloadRequest {
        rawRequest.setDestinationInExternalFilesDir(context, dirType, subPath)
        return this
    }

    fun setDestinationUri(downloadDir: String): DownloadRequest {
        rawRequest.setDestinationUri(Uri.parse(downloadDir))
        return this
    }


    fun setMimeType(mimeType: String): DownloadRequest {
        rawRequest.setMimeType(mimeType)
        return this
    }

    fun setNotificationVisibility(visibility: Int): DownloadRequest {
        rawRequest.setNotificationVisibility(visibility)
        return this
    }

    fun setAllowedNetworkTypes(flags: Int): DownloadRequest {
        rawRequest.setAllowedNetworkTypes(flags)
        return this
    }

    fun setAllowedOverRoaming(allowed: Boolean): DownloadRequest {
        rawRequest.setAllowedOverRoaming(allowed)
        return this
    }

    fun setAllowedOverMetered(allow: Boolean): DownloadRequest {
        rawRequest.setAllowedOverMetered(allow)
        return this
    }

}