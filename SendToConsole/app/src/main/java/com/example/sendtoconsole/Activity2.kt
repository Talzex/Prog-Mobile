package com.example.sendtoconsole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val sendtext = findViewById<TextView>(R.id.textView)
        val extras = intent.extras
        if(extras!= null){
            val value = extras.getString("text")
            sendtext.setText(value)
        }
    }
}