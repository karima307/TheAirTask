package com.example.theairtask.modules.tv_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theairtask.R
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.databinding.FragmentTvListDetailsBinding
import com.example.theairtask.modules.session.SessionViewModel
import com.example.theairtask.modules.tv_details.creators.CreatorAdapter
import com.example.theairtask.modules.tv_details.networks.NetworksAdapter
import com.example.theairtask.modules.tv_list.ResultObject
import com.example.theairtask.modules.tv_list.TVListAdapter
import com.example.theairtask.utils.LoadImage
import com.nmg.baseinfrastructure.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tv_list_details.*
import kotlinx.android.synthetic.main.layout_network_list.view.*
import kotlinx.android.synthetic.main.layout_rating.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.StringBuilder

class TVDetailsFragment : BaseFragment<FragmentTvListDetailsBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_tv_list_details

    val viewModel: TVDetailsViewModel by sharedViewModel()
    val sessionViewModel: SessionViewModel by inject()

    val genres = StringBuilder()
    override fun initUI(savedInstanceState: Bundle?) {

        val tvID = requireArguments().getInt(TV_ID)

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                detailsLoading.visibility = View.VISIBLE
            } else {
                detailsLoading.visibility = View.GONE

            }
        })
        getDetails(tvID)
        submitRate(tvID)
        getRecommendedList(tvID)
    }

    fun setupNetworkList(list: List<Networks>) {

        val networkListAdapter =
            NetworksAdapter(
                requireContext(), list as MutableList<Networks>
            )
        frame_network.idRec.adapter = networkListAdapter


    }

    fun setupCreatorByList(list: List<CreatedBy>) {

        val networkListAdapter =
            CreatorAdapter(
                requireContext(), list as MutableList<CreatedBy>
            )
        frame_created_by.idRec.adapter = networkListAdapter


    }

    fun submitRate(tvID: Int) {
        sessionViewModel.sessionId.observe(viewLifecycleOwner, Observer { sessionResp ->

            rate.setOnRatingBarChangeListener { _, rating, _ ->
                viewModel.observeSubmitRate(
                    tvID,
                    sessionResp.guest_session_id!!,
                    rating.toDouble(),
                    viewLifecycleOwner
                )
            }
        })
        viewModel.success.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(requireContext(),getString(R.string.submit_successfully),Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getDetails(tvID: Int) {
        viewModel.observeTVDtails(tvID, viewLifecycleOwner)
        viewModel.tvList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                tvDetailsTitle.text = it.name
                LoadImage.GlideImageNormal(
                    requireContext(),
                    ApiConst.IMAGE_PATH + it.poster_path,
                    ivTVPosterDetails
                )
                tvRate.text = it.vote_average.toString()
                it.genres!!.forEach {
                    if (genres.isEmpty()) {
                        genres.append(it.name ?: "")
                        genres.append(",")
                    } else {
                        genres.clear()
                        genres.append(it.name ?: "")
                        genres.append(",")
                    }
                }

                tvGenre.text = genres.toString()
                tvEpisodeNumber.text =
                    it.number_of_episodes.toString() + " " + getString(R.string.episode)
                tvHomePage.text = it.homepage
                tvOverview.text = it.overview
                setupNetworkList(it.networks!!)
                setupCreatorByList(it.created_by!!)
            }
        })
    }

    fun getRecommendedList(tvID: Int) {
        viewModel.observeRecommendedTVList(tvID, viewLifecycleOwner)
        viewModel.tvRecommendedList.observe(viewLifecycleOwner, Observer {
            setupRecommendedList(it)
        })
    }

    fun setupRecommendedList(list: List<ResultObject>) {

        val listAdapter = TVListAdapter(
            requireContext(), list as MutableList<ResultObject>
        ) { id ->
            replaceFragment(
                newInstance(id.toInt()),
                R.id.tvActivityContainer,
                true
            )
        }
        frameRecommended.idRec.adapter = listAdapter


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        frame_network.idRec.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        frame_created_by.idRec.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rate.stepSize = 0.5F
        frameRecommended.idRec.layoutManager = GridLayoutManager(context, 2)


    }

    companion object {
        val TV_ID = "TV_ID"
        fun newInstance(tvID: Int) = TVDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(TV_ID, tvID)
            }
        }
    }
}