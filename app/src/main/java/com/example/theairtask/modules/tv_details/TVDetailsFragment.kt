package com.example.theairtask.modules.tv_details

import android.os.Bundle
import com.example.theairtask.R
import com.example.theairtask.databinding.FragmentTvListDetailsBinding
import com.nmg.baseinfrastructure.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TVDetailsFragment :BaseFragment<FragmentTvListDetailsBinding>(){
    override val layoutRes: Int
        get() = R.layout.fragment_tv_list_details

    val viewModel :TVDetailsViewModel by sharedViewModel ()

    override fun initUI(savedInstanceState: Bundle?) {
        val tvID = requireArguments().getInt(TV_ID)
        viewModel.observeTVDtails(tvID,viewLifecycleOwner)

     }

    companion object{
        val TV_ID = "TV_ID"
        fun newInstance(tvID:Int)=TVDetailsFragment().apply {
            arguments =Bundle().apply {
                putInt(TV_ID,tvID)
            }
        }
    }
}