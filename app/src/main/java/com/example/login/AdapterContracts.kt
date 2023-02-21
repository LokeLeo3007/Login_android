package com.example.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.databinding.CustomLayoutListviewBinding

class AdapterContracts(private val newList: List<Contacts>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val news = mutableListOf<Contacts>()

    var onContactSelect: ((position: Int, contact: Contacts) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val itembiding = CustomLayoutListviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MediaItemViewHolder(itembiding, this::onItemClicked)
    }

    private fun onItemClicked(position: Int) {
        onContactSelect?.invoke(position, news[position])
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val selectItem  = news[position]
        when(holder){
            is MediaItemViewHolder ->{
                holder.binData(selectItem,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class MediaItemViewHolder(
        private val customBinding: CustomLayoutListviewBinding,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(customBinding.root) {
        init {
            customBinding.root.setOnClickListener {
                onItemClick.invoke(absoluteAdapterPosition)
            }
        }

        fun binData(data: Contacts, position: Int) {
            customBinding.tvName.text = data.name
            customBinding.tvPhone.text = data.number
        }
    }

//        val isVisible : Boolean = current.isExpanded
//        holder.viewcontacts.visibility = if(isVisible) View.VISIBLE else View.GONE
//        holder.name.setOnClickListener(){
//            current.isExpanded = !current.isExpanded
//            notifyItemChanged(position)
//        }
//        holder.call.setOnClickListener(){
//            val i = Intent(holder.call.context,SecondView::class.java)
//            val bundle = Bundle()
//            bundle.putSerializable()
//            holder.call.context.startActivities(i)
//        }
}

