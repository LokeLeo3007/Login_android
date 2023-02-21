package com.example.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.databinding.ActivityMainBinding
import java.util.zip.Inflater
import javax.net.ssl.SSLEngineResult.Status

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var adapter: AdapterContracts? = null

    var cols = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.inflate(this, R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1){ Manifest.permission.READ_CONTACTS},111)
        }else readContact()
        //read list contacts
        val rs = readContact()
        val list = generateSequence { if (rs?.moveToNext()!!) rs else null }
            .map { Contacts(rs?.getString(0).toString(),rs?.getString(1).toString()) }
            .toList()

//        recyClerView = findViewById(R.id.recyclerHero)
//        recyClerView.layoutManager = LinearLayoutManager(this)
//        recyClerView.setHasFixedSize(true)
        adapter = AdapterContracts(list)
        binding?.recyclerHero?.adapter = adapter
        adapter?.notifyItemChanged(0, list.size)
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
    private fun readContact(): Cursor? {
        return contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, cols, null, null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
    }
}

