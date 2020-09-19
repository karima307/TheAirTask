package com.example.theairtask.modules.tv_list

import com.example.theairtask.data.remote.BaseResponse

class TVListResponse : BaseResponse() {

    val results: List<ResultObject>? = null

    override fun getSuccess(): Any? {
        return results
    }
}

class ResultObject {
    var poster_path: String? = null
    var name: String? = null
    var first_air_date: String? = null
    var vote_average: Double? = null
    var vote_count: Long? = null
    var id: Long? = null
}