package com.chiclaim.android.downloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import com.chiclaim.android.downloader.util.Utils
import com.chiclaim.android.downloader.util.e
import java.io.File


class SystemDownloadRequest(url: String) : Request(url) {

    private var rawRequest = DownloadManager.Request(Uri.parse(url))

    override fun setNotificationTitle(title: CharSequence): Request {
        rawRequest.setTitle(title)
        return this
    }

    override fun setNotificationContent(content: CharSequence): Request {
        rawRequest.setDescription(content)
        return this
    }

    override fun allowScanningByMediaScanner(): Request {
        rawRequest.allowScanningByMediaScanner()
        return this
    }

    override fun setDestinationInExternalFilesDir(
        context: Context,
        dirType: String?,
        subPath: String?
    ): SystemDownloadRequest {
        rawRequest.setDestinationInExternalFilesDir(context, dirType, subPath)
        return this
    }

    /**
     * 必须是外部存储路径，不能是当前应用的黑盒目录（因为是系统应用来下载）
     */
    override fun setDestinationUri(uri: Uri): Request {
        rawRequest.setDestinationUri(uri)
        return super.setDestinationUri(uri)
    }


    override fun setMimeType(mimeType: String): Request {
        rawRequest.setMimeType(mimeType)
        return this
    }

    override fun setNotificationVisibility(visibility: Int): Request {
        rawRequest.setNotificationVisibility(visibility)
        return this
    }

    override fun setAllowedNetworkTypes(flags: Int): Request {
        rawRequest.setAllowedNetworkTypes(flags)
        return this
    }

    override fun setAllowedOverRoaming(allowed: Boolean): Request {
        rawRequest.setAllowedOverRoaming(allowed)
        return this
    }

    override fun setAllowedOverMetered(allow: Boolean): Request {
        rawRequest.setAllowedOverMetered(allow)
        return this
    }

    fun getRequest(): DownloadManager.Request = rawRequest

    override fun buildDownloader(context: Context): Downloader<*> {
        if (DownloaderManager.isRunning(this)) {
            if (BuildConfig.DEBUG) e("下载任务已经存在")
            return EmptyDownloader(context, this)
        }
        if (notificationVisibility != NOTIFIER_HIDDEN && showNotificationDisableTip)
            Utils.checkNotificationsEnabled(context)
        if (destinationUri == null) {
            val filename = url.substringAfterLast("/")
            setDestinationUri(Uri.fromFile(File(Utils.getDownloadDir(context), filename)))
        }
        return SystemDownloader(context.applicationContext, this)
    }

}