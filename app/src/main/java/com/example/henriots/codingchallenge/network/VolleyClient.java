package com.example.henriots.codingchallenge.network;

import android.content.Context;

/**
 * Created by henriots on 21/11/2017.
 */

public class VolleyClient {
    /** Class tag. */
    private static final String TAG = VolleyClient.class.getSimpleName();

    /** URL of ABC news rss feed converted to JSON */
    private static final String NEWS_FEED_URL = "https://api.rss2json.com/v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml";

    /** Context. */
    Context mContext;

    /** Constructor. */
    public VolleyClient(Context mContext) {
        this.mContext = mContext;
    }

}
