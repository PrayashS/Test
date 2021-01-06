package com.Prayash.week5assignment1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Prayash.week5assignment1.R
import com.Prayash.week5assignment1.model.PostData
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class PostAdapter(val context: Context, val postList:MutableList<PostData>): RecyclerView.Adapter<PostAdapter.PostHolder>() {

    class PostHolder(v: View): RecyclerView.ViewHolder(v){
        var ciImage : CircleImageView
        var tvUsername : TextView
        var ivPost : ImageView
        var tvUn : TextView
        var tvDescription : TextView

        init {
            ciImage = v.findViewById(R.id.ciImage)
            tvUsername = v.findViewById(R.id.tvUsername)
            ivPost = v.findViewById(R.id.ivPost)
            tvUn = v.findViewById(R.id.tvUn)
            tvDescription = v.findViewById(R.id.tvDescription)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mero_insta,parent,false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        var postData = postList[position]
        holder.tvDescription.text = postData.description
        holder.tvUsername.text = postData.un
        holder.tvUn.text = postData.un

        Glide.with(context).load(postData.dp).into(holder.ciImage)
        Glide.with(context).load(postData.post).into(holder.ivPost)
    }
}