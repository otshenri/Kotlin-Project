package com.example.henriots.codingchallenge.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by henriots on 21/11/2017.
 */

public class VolleySingleton {
    /** Singleton instance. */
    private static VolleySingleton mInstance;

    /** Queue for Volley requests. */
    private RequestQueue mRequestQueue;

    /** Image loader for loading and caching images. */
    private ImageLoader mImageLoader;

    /** Context. */
    private static Context mContext;
}
