package com.example.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes


class Contacts_adapter(context: Context,
                         @LayoutRes private val layoutResource: Int,
                         private val values: List<Contacts>) : ArrayAdapter<Contacts>(context, layoutResource, values) {

    override fun getItem(position: Int): Contacts = values[position]
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_listview, parent, false)
        }
        var contacts = getItem(position)
        var tv1 = view?.findViewById<TextView>(R.id.textView1)
        tv1?.setText(contacts?.name)

        // then according to the position of the view assign the desired TextView 2 for the same

        // then according to the position of the view assign the desired TextView 2 for the same
        var tv2 = view?.findViewById<TextView>(R.id.textView1)
        tv2?.setText(contacts?.name)
        return bindData(getItem(position), view as TextView)
    }
    private fun bindData(value: Contacts, view: TextView): TextView {
        view.text = value.name
        return view
    }
}