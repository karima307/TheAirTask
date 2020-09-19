package com.example.theairtask.data.remote

import com.nmg.baseinfrastructure.data.remote.APIConfig
import com.nmg.baseinfrastructure.data.remote.ErrorHandler
import java.util.HashMap

class ApiConfig: APIConfig(){



    override fun getHost(): String =ApiConst.BASE_URL
    override fun getBaseUrl(): String =ApiConst.BASE_URL
    override fun <T> getApiService(): Class<T>  = ApiService::class.java as Class<T>

    override fun getHeaders(): HashMap<String, String>? {

        val headers = HashMap<String,String>()

        return headers
    }

    override fun getErrorHandler(): ErrorHandler?  = NetworkErrorHandler()

    override fun getAppName(): String  =""

    override fun isInternetConnected(): Boolean = true

    override fun getAppVersion(): String  = ""
}