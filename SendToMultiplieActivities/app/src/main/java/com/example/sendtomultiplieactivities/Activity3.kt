package com.example.sendtomultiplieactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        var adressrecup =""
        var zipcoderecup = ""
        var cityrecup=""


        //Récupération valeur Activté Principale
        val extras = intent.extras
        if(extras!= null){
            if(extras.get("address").toString() != null
                && extras.get("zipcode").toString() != null
                && extras.get("ville").toString() != null){
                adressrecup = extras.get("address").toString()
                zipcoderecup = extras.get("zipcode").toString()
                cityrecup = extras.get("ville").toString()
                findViewById<TextView>(R.id.editTextAdress).text = extras.get("address").toString()
                findViewById<TextView>(R.id.editTextzipcode).text = extras.get("zipcode").toString()
                findViewById<TextView>(R.id.editTextCity).text = extras.get("ville").toString()
            }
        }

        val buttoncancel = findViewById<Button>(R.id.buttoncancel2)
        val buttonconfirm = findViewById<Button>(R.id.buttonconfirm2)

        //Envoie valeur vers Activté Principale
        buttonconfirm.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (findViewById<EditText>(R.id.editTextAdress).text.toString() != ""
                    && findViewById<EditText>(R.id.editTextCity).text.toString() != ""
                    && findViewById<EditText>(R.id.editTextzipcode).text.toString() != "") {
                    val intent = Intent(this@Activity3, MainActivity::class.java)
                    intent.putExtra("address", findViewById<EditText>(R.id.editTextAdress).text.toString())
                    intent.putExtra("zipcode", findViewById<EditText>(R.id.editTextzipcode).text.toString())
                    intent.putExtra("ville", findViewById<EditText>(R.id.editTextCity).text.toString())
                    startActivity(intent)
                }
            }
        })

        //Envoie ancienne valeur vers Activité Principale
        buttoncancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Activity3, MainActivity::class.java)
                intent.putExtra("address", adressrecup)
                intent.putExtra("zipcode", zipcoderecup)
                intent.putExtra("ville", cityrecup)
                startActivity(intent)
            }
        })
    }
}