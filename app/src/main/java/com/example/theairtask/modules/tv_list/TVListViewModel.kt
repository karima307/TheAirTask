package com.example.theairtask.modules.tv_list

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nmg.baseinfrastructure.base.BaseViewModel
import com.nmg.baseinfrastructure.data.remote.Status

class TVListViewModel(val repo: TVListRepo) : BaseViewModel() {

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val tvList = MutableLiveData<List<ResultObject>>()

    private fun getTvList() = repo.getTVList()

    fun observeTvList(lifecycleOwner: LifecycleOwner) {
        getTvList().observe(lifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.value = true
                    error.value = false

                }
                Status.SUCCESS -> {
                    if (it.data!!.results != null) {
                        loading.value = false
                        error.value = false
                        tvList.value = it.data!!.results
                    }
                }
                Status.ERROR -> {
                    loading.value = false
                    error.value = true

                }
            }
        })
    }

}