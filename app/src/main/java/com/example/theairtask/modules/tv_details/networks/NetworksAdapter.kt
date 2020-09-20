package com.example.theairtask.modules.tv_details.networks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theairtask.R
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.modules.tv_details.BaseInfo
import com.example.theairtask.modules.tv_details.Networks
import com.example.theairtask.utils.LoadImage
import kotlinx.android.synthetic.main.item_newtorks.view.*


class NetworksAdapter(
    val context: Context,
    val list: MutableList<Networks>
) : RecyclerView.Adapter<NetworksAdapter.NetworksAndProductionAdapterHolder>() {


    inner class NetworksAndProductionAdapterHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
        val tvLogo = itemView.imageLogo


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NetworksAndProductionAdapterHolder {
        return NetworksAndProductionAdapterHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_newtorks, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: NetworksAndProductionAdapterHolder, position: Int) {
        val item = list[position]
        holder.tvName.text = item.name

        LoadImage.GlideImageNormal(context, ApiConst.IMAGE_PATH + item.logo_path, holder.tvLogo)

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}