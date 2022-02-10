package com.example.testimplicit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity  : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonsms = findViewById<Button>(R.id.button)
        buttonsms.setOnClickListener(this)
        val buttonmms = findViewById<Button>(R.id.button2)
        buttonmms.setOnClickListener(this)
        val buttoncall = findViewById<Button>(R.id.button3)
        buttoncall.setOnClickListener(this)
        val buttonweb = findViewById<Button>(R.id.button4)
        buttonweb.setOnClickListener(this)
        val buttonmap = findViewById<Button>(R.id.button5)
        buttonmap.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            val phonenb = findViewById<EditText>(R.id.editTextPhone).text.toString()
            val url = findViewById<EditText>(R.id.editTextURL).text.toString()
            val lat = findViewById<EditText>(R.id.editTextLatitude).text.toString().toInt()
            val long = findViewById<EditText>(R.id.editTextLongitude).text.toString().toInt()
            when(v.id) {
                R.id.button -> {
                    if (phonenb != null){
                        val intent = Intent(Intent.ACTION_SENDTO,Uri.fromParts("smsto",phonenb,null));
                        intent.putExtra("sms_body", "The SMS text")
                        startActivity(intent)
                    }

                }
                R.id.button2 -> {
                    if (phonenb != null){
                        val mmsintent = Intent(Intent.ACTION_SENDTO,Uri.fromParts("mmsto", phonenb, null));
                        mmsintent.putExtra("sms_body", "The MMS text")
                        startActivity(mmsintent)
                    }

                }
                R.id.button3 -> {
                    if (phonenb != null){
                        val callintent = Intent(Intent.ACTION_DIAL,Uri.fromParts("tel", phonenb, null));
                        startActivity(callintent)
                    }

                }
                R.id.button4 -> {
                    if (url != null){
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(browserIntent)
                    }

                }
                R.id.button5 -> {
                    if (lat != null && long != null){
                        val mapintent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat,$long"))
                        mapintent.setPackage("com.google.android.apps.maps")
                        startActivity(mapintent)
                    }

                }
                }
            }
        }
}