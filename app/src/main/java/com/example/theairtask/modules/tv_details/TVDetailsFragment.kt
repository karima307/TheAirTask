package com.example.theairtask.modules.tv_details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theairtask.R
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.databinding.FragmentTvListDetailsBinding
import com.example.theairtask.modules.tv_details.creators.CreatorAdapter
import com.example.theairtask.modules.tv_details.networks.NetworksAdapter
import com.example.theairtask.utils.LoadImage
import com.nmg.baseinfrastructure.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tv_list_details.*
import kotlinx.android.synthetic.main.layout_network_list.view.*
import kotlinx.android.synthetic.main.layout_rating.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.StringBuilder

class TVDetailsFragment : BaseFragment<FragmentTvListDetailsBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_tv_list_details

    val viewModel: TVDetailsViewModel by sharedViewModel()
    val genres = StringBuilder()
    override fun initUI(savedInstanceState: Bundle?) {

        val tvID = requireArguments().getInt(TV_ID)
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
                    genres.append(it.name ?: "")
                    genres.append(",")
                }

                tvGenre.text = genres.toString()
                tvEpisodeNumber.text = it.number_of_episodes.toString() + " "+getString(R.string.episode)
                tvHomePage.text = it.homepage
                tvOverview.text = it.overview
                setupNetworkList(it.networks!!)
                setupCreatorByList(it.created_by!!)
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it){
                detailsLoading.visibility = View.VISIBLE
            }
            else{
                detailsLoading.visibility = View.GONE

            }
        })

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        frame_network.idRec.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        frame_created_by.idRec.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)

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