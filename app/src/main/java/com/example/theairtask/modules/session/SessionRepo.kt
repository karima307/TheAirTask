package com.example.theairtask.modules.session

import androidx.lifecycle.LiveData
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.data.remote.ApiService
import com.example.theairtask.modules.tv_details.TVDetailsResponse
import com.nmg.baseinfrastructure.data.remote.ApiResponse
import com.nmg.baseinfrastructure.data.remote.ContextProviders
import com.nmg.baseinfrastructure.data.remote.NetworkBoundResource
import com.nmg.baseinfrastructure.data.remote.Resource

class SessionRepo (val apiService: ApiService, val contextProviders: ContextProviders) {
    fun getSessionId(): LiveData<Resource<SessionResponse>> {
        return object : NetworkBoundResource<SessionResponse, SessionResponse>(contextProviders) {
            private var response: SessionResponse? = null
            override fun saveCallResult(item: SessionResponse) {
                response = item
            }

            override fun getResult(): SessionResponse? = response
            override fun createCall(): LiveData<ApiResponse<SessionResponse>> {
                return apiService.getGuestSessionId( ApiConst.TOKEN)
            }

            override fun shouldFetch(data: SessionResponse?): Boolean = true
            override fun loadFromDb(): LiveData<SessionResponse>? = null
        }.asLiveData()
    }
}