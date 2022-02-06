package com.example.sendtoconsole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<EditText>(R.id.editTextTextPersonName)
        val buttonsend = findViewById<Button>(R.id.button)
        buttonsend.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                System.out.println(text.text.toString())
                val intent = Intent(this@MainActivity, Activity2::class.java)
                intent.putExtra("text", text.text.toString())
                startActivity(intent)
            }
        })
    }
}