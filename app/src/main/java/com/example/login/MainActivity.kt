package com.example.login


import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

//    private val userName = "admin";
//    private val Password = "test";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1){android.Manifest.permission.READ_CONTACTS},111)
        }else readContact()

        val rs = readContact()

        val list = generateSequence { if (rs?.moveToNext()!!) rs else null }
            .map { Contacts(rs?.getString(0).toString(),rs?.getString(1).toString()) }
            .toList()

        var listViewProduct = findViewById<ListView>(R.id.listproduct)
        var adapter = Contacts_adapter(this,android.R.layout.simple_list_item_1,list)
        listViewProduct.adapter = adapter

//
//        btn.setOnClickListener {
//            var x = 0
//            if(rs?.moveToNext()!!){
//                myIntent.putExtra(x.toString(),rs.getString(0) +" "+rs.getString(1))
//                startActivity(myIntent)
//                rs = rs.moveToNext()
//                x++
//            }
//            setContentView(R.layout.secondview)
//            val myIntent = Intent(this, SecondView::class.java)

//            while(rs?.moveToNext()!!){
//                myIntent.putExtra("key",)
//            }

//            myIntent.putExtra("key", readContact())
//            startActivity(myIntent)
//        }
//            var messenger = ""
//            var username = findViewById<ConstraintLayout>(R.id.Username) as EditText
//            var password = findViewById<ConstraintLayout>(R.id.Password) as EditText
//            if (username.text.toString() == userName && password.text.toString() == Password) {
//                messenger = "Success"
//                setContentView(R.layout.secondview)
//            } else {
//                messenger = "Fail"
//            }
//
//            Toast.makeText(this@MainActivity, messenger, Toast.LENGTH_SHORT).show()


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
