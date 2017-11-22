package com.example.henriots.codingchallenge.interfaces

import com.example.henriots.codingchallenge.models.Article
import org.json.JSONObject

/**
 * Created by henriots on 22/11/2017.
 */
interface ServiceInterface {
    /** GET method.  */
    fun get(path: String, completionHandler: (response: JSONObject?) -> Unit)
}