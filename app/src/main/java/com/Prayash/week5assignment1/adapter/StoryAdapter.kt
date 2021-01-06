package com.Prayash.week5assignment1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.Prayash.week5assignment1.R
import com.Prayash.week5assignment1.model.StoryData
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class StoryAdapter(val context: Context,val storyList:MutableList<StoryData>):RecyclerView.Adapter<StoryAdapter.StoryHolder>() {

    class StoryHolder(v:View):RecyclerView.ViewHolder(v)
    {
        var dp: CircleImageView
        var username : TextView

        init {
            dp = v.findViewById(R.id.imgDp)
            username = v.findViewById(R.id.tvUsername)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mero_story,parent,false)
        return StoryHolder(view)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: StoryHolder, position: Int) {
        val holdData = storyList[position]
        holder.username.text = holdData.userName

        Glide.with(context).load(holdData.img).into(holder.dp)



    }


}