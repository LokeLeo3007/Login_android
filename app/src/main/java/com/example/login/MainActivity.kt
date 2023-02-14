package com.example.login

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

//    private val userName = "admin";
//    private val Password = "test";

//    private val adapter = AdapterContracts(this::onTitleClick)
//
//    private fun onTitleClick(news: Contacts, position: Int) {
//        news.isExpanded = !news.isExpanded
//        adapter.notifyItemChanged(position)
//    }

    private lateinit var newRecycleview : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1){ Manifest.permission.READ_CONTACTS},111)
        }else readContact()

        val rs = readContact()

        val list = generateSequence { if (rs?.moveToNext()!!) rs else null }
            .map { Contacts(rs?.getString(0).toString(),rs?.getString(1).toString()) }
            .toList()

        newRecycleview = findViewById(R.id.recyclerHero)
        newRecycleview.layoutManager = LinearLayoutManager(this)
        newRecycleview.setHasFixedSize(true)
        newRecycleview.adapter = AdapterContracts(list)

    }


    var cols = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readContact()
        }
    }

    private fun readContact() : Cursor? {
        var rs = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,null,null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        return rs
    }
}
