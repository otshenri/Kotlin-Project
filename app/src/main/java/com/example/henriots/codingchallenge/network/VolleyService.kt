package com.example.henriots.codingchallenge.network

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.example.henriots.codingchallenge.interfaces.ServiceInterface
import com.example.henriots.codingchallenge.models.ArticleParser
import org.json.JSONObject

/**
 * Created by henriots on 22/11/2017.
 */
class VolleyService : ServiceInterface {
    /** This class defines volley requests. */

    /** Class tag. */
    val TAG = VolleyService::class.java.simpleName;

    /** Path to ABC News rss feed converted to JSON*/
    val PATH = "https://api.rss2json.com/v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml"

    /** JSON parser. Would prefer GSON library for JSON parsing but only Volley was allowed. */
    val jsonParser = ArticleParser()

    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.GET, path, null,
                Response.Listener<JSONObject> { response ->
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "Get request failed! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        VolleySingleton.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}