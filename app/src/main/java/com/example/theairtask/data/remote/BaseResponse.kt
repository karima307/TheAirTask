package com.example.theairtask.data.remote

import com.nmg.baseinfrastructure.data.remote.BaseResponseModel

open class BaseResponse: BaseResponseModel() {
    var success: Boolean = false

    var error: NetworkError? = null

    override fun getError(): String? {
        return error?.details ?: error?.message ?: ""
    }

    class NetworkError {
        var code: Int? = null
        var message: String? = null
        var details: String? = null
        var validationErrors: List<ValidationError>? = null

    }

    override fun getSuccess(): Any? {
        return if (success) success else null
    }


}

data class ValidationError(
    val message: String? = null
)
