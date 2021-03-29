package com.example.userregistrationapplication.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.userregistrationapplication.R
import com.example.userregistrationapplication.model.User
import kotlinx.android.synthetic.main.row_fragment.view.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_fragment, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.firstname_text.text = currentItem.firstName
        holder.itemView.lastname_text.text = currentItem.lastName
        holder.itemView.age_text.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}