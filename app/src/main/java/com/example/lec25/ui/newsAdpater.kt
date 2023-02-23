package com.example.lec25.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lec25.R

class newsAdpater(val activity:Activity,val articles:ArrayList<Article>) :RecyclerView.Adapter<newsAdpater.newsVH>(){
    class newsVH(view:View) :RecyclerView.ViewHolder(view){
val parent:CardView=view.findViewById(R.id.parent)
        val image:ImageView=view.findViewById(R.id.news_iv)
        val  title:TextView=view.findViewById(R.id.news_tv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsVH {
val view=activity.layoutInflater.inflate(R.layout.news_listitem,parent,false)
        return newsVH(view)
    }
    override fun onBindViewHolder(holder: newsVH, position: Int) {
        val link =articles.get(position).urlToImage
        if(link==null)
            holder.image.setImageResource(R.drawable.ic_baseline_broken_image_24)
        else
             Glide
    .with(activity)
    .load(articles.get(position).urlToImage).into(holder.image)
        holder.title.text=articles.get(position).title
        holder.parent.setOnClickListener {
            val i=Intent(Intent.ACTION_VIEW,articles.get(position).url.toUri())
            activity.startActivity(i)
        }
    }
    override fun getItemCount(): Int =articles.size
}