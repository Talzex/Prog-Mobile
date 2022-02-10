package com.example.testimplicit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

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
            val phone = "+33618041926"
            when(v.id) {
                R.id.button -> {
                    val intent = Intent(Intent.ACTION_SENDTO,Uri.fromParts("smsto",phone,null));
                    intent.putExtra("sms_body", "The SMS text")
                    startActivity(intent)
                }

                    R.id.button2 -> {
                        val mmsintent = Intent(Intent.ACTION_SENDTO,Uri.fromParts("mmsto", phone, null));
                        mmsintent.putExtra("sms_body", "The MMS text")
                        startActivity(mmsintent)
                    }
                    R.id.button3 -> {
                        val callintent = Intent(Intent.ACTION_DIAL,Uri.fromParts("tel", phone, null));
                        startActivity(callintent)
                    }
                    R.id.button4 -> {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                        startActivity(browserIntent)
                    }
                    R.id.button5 -> {
                        val mapintent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+CA+94043"))
                        startActivity(mapintent)
                    }
                }
            }
        }
}