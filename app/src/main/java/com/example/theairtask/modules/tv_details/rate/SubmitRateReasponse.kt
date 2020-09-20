package com.example.theairtask.modules.tv_details.rate

import com.example.theairtask.data.remote.BaseResponse

class SubmitRateReasponse : BaseResponse() {
    val status: Boolean? = null
    val status_code: Int? = null
    val status_message: String? = null
    override fun getSuccess(): Any? {
        return status
    }
}