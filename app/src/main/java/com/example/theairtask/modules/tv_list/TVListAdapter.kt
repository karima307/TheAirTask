package com.example.theairtask.modules.tv_list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.theairtask.R
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.utils.LoadImage
import kotlinx.android.synthetic.main.item_tv_list.view.*
import java.lang.Exception

class TVListAdapter(
    val context: Context,
    val list: MutableList<ResultObject>,
    val onItemClickListener: (itemID: Long) -> Unit
) : RecyclerView.Adapter<TVListAdapter.TVListAdapterHolder>() {


    inner class TVListAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tvTitle
        val tvDate = itemView.tvDate
        val tvPostr = itemView.ivPoster
        val tvVote = itemView.tvRate



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVListAdapterHolder {
        return TVListAdapterHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: TVListAdapterHolder, position: Int) {
        val item = list[position]

        holder.tvTitle.text = item.name
        holder.tvDate.text = item.first_air_date
        holder.tvVote.text = item.vote_average.toString()
        LoadImage.GlideImageNormal(context, ApiConst.IMAGE_PATH + item.poster_path, holder.tvPostr)
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(item.id!!)
        }


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}