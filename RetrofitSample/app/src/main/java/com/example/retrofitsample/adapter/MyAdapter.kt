package com.example.retrofitsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.model.Post
import com.example.retrofitsample.R
import kotlinx.android.synthetic.main.row_layout.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.userId_textView.text = myList[position].myUser.toString()
        holder.itemView.Id_textView.text = myList[position].id.toString()
        holder.itemView.body_textView.text = myList[position].body
        holder.itemView.title_textView.text = myList[position].title
    }

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}