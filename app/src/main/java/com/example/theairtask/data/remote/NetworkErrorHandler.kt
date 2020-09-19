package com.example.theairtask.data.remote

 import android.util.Log
 import android.util.MalformedJsonException
 import com.bumptech.glide.load.HttpException
 import com.example.theairtask.App
 import com.example.theairtask.R
 import com.google.gson.Gson

 import com.nmg.baseinfrastructure.data.remote.ErrorHandler
  import java.io.IOException
 import java.net.ConnectException
  import java.net.UnknownHostException

class NetworkErrorHandler : ErrorHandler {
    override fun getErrorFromBody(errorBody: String?): String? {
        try {
            return Gson().fromJson(errorBody, ErrorBaseResonse::class.java).getError()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return getHttpExceptionError(ex)
        }
    }


   override fun getHttpExceptionError(error: Throwable): String?{

        Log.e("ErrorHandler" , error.message?:"")

        val context = App.appInstance.getAndroidContext()

        return if (error is MalformedJsonException) {
            context.getString(R.string.unknownError)
        } else if (error is UnknownHostException) {
            context.getString(R.string.unknownError)
        } else if (error is ConnectException) {
            context.getString(R.string.networkError)
        } else if (error is IOException) {
            context.getString(R.string.unknownError)
        } else if (error is HttpException) {
            context.getString(R.string.unknownError)
        }
         else {
            context.getString(R.string.unknownError)
        }
    }

}