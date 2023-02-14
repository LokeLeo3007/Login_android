package com.example.login

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBindings


class CustomArrayAdapter(context: Context,
                         @LayoutRes private val layoutResource: Int,
                         @IdRes private val textViewResourceId: Int = 0,
                         private val values: List<Contacts>) : ArrayAdapter<Contacts>(context, layoutResource, values) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_listview, parent, false)
        }
//        var contacts = getItem(position)
//        var tv1 = view?.findViewById<TextView>(R.id.textView1)
//        tv1?.setText(contacts?.name)
//
//        // then according to the position of the view assign the desired TextView 2 for the same
//
//        // then according to the position of the view assign the desired TextView 2 for the same
//        val textView2: TextView = currentItemView.findViewById(R.id.textView2)
//        textView2.setText(currentNumberPosition.getNumbersInText())
    }

}