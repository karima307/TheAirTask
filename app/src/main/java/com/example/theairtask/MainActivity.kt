package com.example.theairtask

import android.os.Bundle
import android.os.PersistableBundle
import com.example.theairtask.databinding.ActivityMainBinding
import com.example.theairtask.modules.tv_list.TVListFragment
import com.nmg.baseinfrastructure.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_main


    override fun initUI(savedInstanceState: Bundle?) {
        replaceFragment(TVListFragment.newInstance(), R.id.tvActivityContainer, false)
    }

    override fun showSuccessMessage(message: String, onPositiveActionClickListener: () -> Unit) {
    }

    override fun showSuccessMessage(
        message: String,
        onPositiveActionClickListener: () -> Unit,
        onNegativeActionClickListener: () -> Unit
    ) {
    }
}