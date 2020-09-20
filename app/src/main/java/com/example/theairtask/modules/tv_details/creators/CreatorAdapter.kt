package com.example.theairtask.modules.tv_details.creators

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theairtask.R
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.modules.tv_details.BaseInfo
import com.example.theairtask.modules.tv_details.CreatedBy
import com.example.theairtask.utils.LoadImage
import kotlinx.android.synthetic.main.item_creatoe.view.*


class CreatorAdapter (
    val context: Context,
    val list: MutableList<CreatedBy>
) : RecyclerView.Adapter<CreatorAdapter.CreatorAdapterHolder>() {


    inner class CreatorAdapterHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvCreatorName
        val tvLogo = itemView.imageCreator


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreatorAdapterHolder {
        return CreatorAdapterHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_creatoe, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: CreatorAdapterHolder, position: Int) {
        val item = list[position]
        holder.tvName.text = item.name

        LoadImage.GlideImageNormal(context, ApiConst.IMAGE_PATH + item.profile_path, holder.tvLogo)

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}