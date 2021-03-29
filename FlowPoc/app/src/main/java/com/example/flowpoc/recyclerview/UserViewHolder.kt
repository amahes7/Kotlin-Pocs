package com.example.flowpoc.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowpoc.R
import com.example.flowpoc.model.ResponseObject
import kotlinx.android.synthetic.main.layout_single_item.view.*

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    
//    fun bindTo(user: ResponseObject.Users){
//        itemView.name.text = user.first_name
//        Glide
//            .with(itemView)
//            .load(user.avatar)
//            .centerCrop()
//            .placeholder(R.drawable.user_avatar)
//            .into(itemView.image);
//    }


    fun bindTo(user: ResponseObject.Users2){
        itemView.name.text = user.first_name
        Glide
            .with(itemView)
            .load(user.avatar)
            .centerCrop()
            .placeholder(R.drawable.user_avatar)
            .into(itemView.image);
    }
}