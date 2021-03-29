package com.example.flowpoc.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.flowpoc.R
import com.example.flowpoc.model.ResponseObject

//class UsersAdapter : ListAdapter<ResponseObject.Users, UserViewHolder>(UserItemDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder {
//        return UserViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.layout_single_item, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bindTo(getItem(position))
//    }
//}


class UsersAdapter : ListAdapter<ResponseObject.Users2, UserViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_single_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}