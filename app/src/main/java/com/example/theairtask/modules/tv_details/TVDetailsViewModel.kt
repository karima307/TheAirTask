package com.example.theairtask.modules.tv_details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.theairtask.modules.tv_list.ResultObject
import com.nmg.baseinfrastructure.base.BaseViewModel
import com.nmg.baseinfrastructure.data.remote.Status

class TVDetailsViewModel(val repo: TVDetailsRepo):BaseViewModel() {

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val success = MutableLiveData<Boolean>()
    val tvList = MutableLiveData<TVDetailsResponse>()

    private fun getTVDtails(tvID:Int) = repo.getTVDtails(tvID)
    private fun submitRate(tvId:Int , sessionID:String , rate:Double) = repo.submitRate(tvId,sessionID,rate)

    fun observeTVDtails(tvID:Int,lifecycleOwner: LifecycleOwner) {
        getTVDtails(tvID).observe(lifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.value = true
                    error.value = false

                }
                Status.SUCCESS -> {
                         loading.value = false
                        error.value = false
                        tvList.value = it.data!!
                 }
                Status.ERROR -> {
                    loading.value = false
                    error.value = true

                }
            }
        })
    }
    fun observeSubmitRate(tvId:Int , sessionID:String , rate:Double,lifecycleOwner: LifecycleOwner) {
        submitRate(tvId,sessionID,rate).observe(lifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.value = true
                    error.value = false
                    error.value = false

                }
                Status.SUCCESS -> {
                         loading.value = false
                        error.value = false
                        success.value = true
                 }
                Status.ERROR -> {
                    loading.value = false
                    error.value = true
                    error.value = false

                }
            }
        })
    }

}