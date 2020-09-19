package com.example.theairtask.data.remote

class ErrorBaseResonse :BaseResponse() {
    override fun getSuccess(): Any? {
        return false
    }
}