package com.example.theairtask.modules.tv_details

import com.example.theairtask.data.remote.BaseResponse

class TVDetailsResponse : BaseResponse() {
    override fun getSuccess(): Any? {
        return id
    }

    val id: Int? = null
    val homepage: String? = null
    val overview: String? = null
    val name: String? = null
    val number_of_episodes: Int? = null
    val vote_average: Double? = null
    val vote_count: Long? = null
    val poster_path: String? = null
    val genres: List<Genres>? = null
    val networks: List<Networks>? = null
    val production_companies: List<ProductionCompanies>? = null
    val created_by: List<CreatedBy>? = null

}

class Genres {
    val id: Int? = null
    val name: String? = null
}

class Networks : BaseInfo()
class ProductionCompanies : BaseInfo()
open class BaseInfo {
    val id: Int? = null
    val name: String? = null
    val logo_path: String? = null
    val origin_country: String? = null
}

data class CreatedBy(

    val id: Int,
    val credit_id: String,
    val name: String,
    val gender: Int,
    val profile_path: String
)