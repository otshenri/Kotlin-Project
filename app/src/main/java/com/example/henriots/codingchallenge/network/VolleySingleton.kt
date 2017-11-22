package com.example.henriots.codingchallenge.network

import android.app.Application
import android.text.TextUtils
import android.graphics.Bitmap
import android.util.LruCache

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.ImageLoader



/**
 * Created by henriots on 22/11/2017.
 */
class VolleySingleton : Application() {
    /** This singleton takes care of request queueing, cancelling etc.  */

    /** Singleton is initiated at app start. */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    /** Request queue for network requests.  */
    val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    /** Imageloader for loading images async and caching them.  */
    val imageLoader: ImageLoader by lazy { ImageLoader(requestQueue, LruBitmapCache())}

    fun <T> addToRequestQueue(request: Request<T>, tag: String) {
        request.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(request)
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        request.tag = TAG
        requestQueue?.add(request)
    }

    fun cancelPendingRequests(tag: Any) {
        if (requestQueue != null) {
            requestQueue!!.cancelAll(tag)
        }
    }

    companion object {
        private val TAG = VolleySingleton::class.java.simpleName
        @get:Synchronized var instance: VolleySingleton? = null
            private set
    }

    /*
     * Class for caching images
     */
    class LruBitmapCache constructor(sizeInKB: Int = defaultLruCacheSize):
            LruCache<String, Bitmap>(sizeInKB), ImageLoader.ImageCache {

        override fun sizeOf(key: String, value: Bitmap): Int = value.rowBytes * value.height / 1024

        override fun getBitmap(url: String): Bitmap? {
            return get(url)
        }

        override fun putBitmap(url: String, bitmap: Bitmap) {
            put(url, bitmap)
        }

        companion object {
            val defaultLruCacheSize: Int
                get() {
                    val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
                    val cacheSize = maxMemory / 8
                    return cacheSize
                }
        }
    }
}