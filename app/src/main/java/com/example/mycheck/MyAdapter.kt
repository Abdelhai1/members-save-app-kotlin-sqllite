package com.example.mycheck

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var userlist : ArrayList<Datalist>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun OnItemClick(position: Int)
    }
    fun onItemClickListener(listener : onItemClickListener){
        mListener = listener
    }
    class MyViewHolder(itemView : View,listener :onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val tname : TextView = itemView.findViewById(R.id.textView4)
        val tage : TextView = itemView.findViewById(R.id.textView6)
        val tsub : TextView = itemView.findViewById(R.id.textView7)

        init {
            itemView.setOnClickListener {
                listener.OnItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return  MyViewHolder(itemView,mListener)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userlist[position]
        holder.tname.text = currentItem.name
        holder.tage.text = currentItem.age
        holder.tsub.text = currentItem.sub
    }
}