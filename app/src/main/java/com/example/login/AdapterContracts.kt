package com.example.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterContracts(private val newList: List<Contacts>): RecyclerView.Adapter<AdapterContracts.ViewHolder>() {
    private val news = mutableListOf<Contacts>()
    class ViewHolder(itemview : View): RecyclerView.ViewHolder(itemview) {
        val name : TextView = itemview.findViewById<TextView>(R.id.textView1)
        val number : TextView = itemview.findViewById<TextView>(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_listview,parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = newList[position]
        holder.name.text = current.name
        holder.number.text = current.number
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}