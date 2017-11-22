package com.example.henriots.codingchallenge.network

import com.example.henriots.codingchallenge.interfaces.ServiceInterface
import com.example.henriots.codingchallenge.models.Article
import org.json.JSONObject

/**
 * Created by henriots on 22/11/2017.
 */
class ApiController constructor(serviceInterface: ServiceInterface): ServiceInterface {
    /**
     * This class decouples Volley dependency from the project making mocking easier.
     * Also easy to change networking library in the future.
     */

    val service: ServiceInterface = serviceInterface

    /** GET method.  */
    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        service.get(path, completionHandler)
    }
}