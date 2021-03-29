package com.example.flowpoc.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.flowpoc.model.ResponseObject

//class UserItemDiffCallback : DiffUtil.ItemCallback<ResponseObject.Users>() {
//
//    override fun areItemsTheSame
//                (oldItem: ResponseObject.Users, newItem: ResponseObject.Users): Boolean
//            = oldItem.id == newItem.id
//
//    override fun areContentsTheSame
//                (oldItem: ResponseObject.Users, newItem: ResponseObject.Users): Boolean
//            = oldItem == newItem
//}



class UserItemDiffCallback : DiffUtil.ItemCallback<ResponseObject.Users2>() {

    override fun areItemsTheSame
                (oldItem: ResponseObject.Users2, newItem: ResponseObject.Users2): Boolean
            = oldItem.id == newItem.id

    override fun areContentsTheSame
                (oldItem: ResponseObject.Users2, newItem: ResponseObject.Users2): Boolean
            = oldItem == newItem
}