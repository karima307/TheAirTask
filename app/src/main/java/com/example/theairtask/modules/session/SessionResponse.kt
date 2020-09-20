package com.example.theairtask.modules.session

import com.example.theairtask.data.remote.BaseResponse

class SessionResponse:BaseResponse() {
    val guest_session_id : String?= null
   val expires_at : String?=null
    override fun getSuccess(): Any? {
        return guest_session_id
    }
}