package com.example.theairtask.modules.session

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nmg.baseinfrastructure.data.remote.Status

class SessionViewModel(val repo: SessionRepo) {
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val sessionId = MutableLiveData<SessionResponse>()
    private fun getSessionId() = repo.getSessionId()

    fun observeGetSessionId(lifecycleOwner: LifecycleOwner) {
        getSessionId().observe(lifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.value = true
                }
                Status.SUCCESS -> {
                    loading.value = false
                    sessionId.value = it.data!!
                }
                Status.ERROR -> {
                    loading.value = false

                }
            }
        })
    }


}