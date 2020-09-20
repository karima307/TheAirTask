package com.example.theairtask.modules.tv_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theairtask.R
import com.example.theairtask.databinding.FragmentTvListBinding
import com.example.theairtask.modules.session.SessionViewModel
import com.example.theairtask.modules.tv_details.TVDetailsFragment
import com.nmg.baseinfrastructure.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tv_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TVListFragment : BaseFragment<FragmentTvListBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_tv_list
    val tvListViewModel: TVListViewModel by sharedViewModel()
    val sessionViewModel: SessionViewModel by inject ()
    override fun initUI(savedInstanceState: Bundle?) {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rcTVList.layoutManager = GridLayoutManager(context, 2)

        tvListViewModel.observeTvList(viewLifecycleOwner)
        tvListViewModel.tvList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setupList(it)
            }
        })
        tvListViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                tv_list_progress.visibility = View.VISIBLE
            } else {
                tv_list_progress.visibility = View.GONE

            }
        })
        sessionViewModel.observeGetSessionId(viewLifecycleOwner)

    }

    companion object {
        fun newInstance() = TVListFragment()
    }

    fun setupList(list: List<ResultObject>) {

        val listAdapter = TVListAdapter(
            requireContext(), list as MutableList<ResultObject>
        ) { id ->
            replaceFragment(
                TVDetailsFragment.newInstance(id.toInt()),
                R.id.tvActivityContainer,
                true
            )
        }
        rcTVList.adapter = listAdapter


    }
}