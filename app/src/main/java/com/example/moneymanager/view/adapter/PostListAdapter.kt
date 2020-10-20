package com.example.moneymanager.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moneymanager.BR
import com.example.moneymanager.R
import com.example.moneymanager.databinding.ItemListPostBinding
import com.example.moneymanager.model.PostInfo

class PostListAdapter(var context: Context) : RecyclerView.Adapter<PostListAdapter.ViewHolder>(){
    var list = emptyList<PostInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemListPostBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_post,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setAdapterList(list: List<PostInfo>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : ItemListPostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Any){
            binding.setVariable(BR.postmodel,data)
            binding.executePendingBindings()
            Glide.with(binding.root.context)
                .load("https://www.iams.com/images/default-source/article-image/article_puppy-basics-selecting-the-right-food_header.jpg")
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.imageData)
        }
    }
}