package com.example.sendtomultiplieactivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        var nomrecup =""
        var prenomrecup = ""
        var phonerecup=""

        //Récupération valeur Activté Principale
        val extras = intent.extras
        if(extras!= null){
            if(extras.get("nom").toString() != null
                && extras.get("prenom").toString() != null
                && extras.get("phone").toString() != null){
                nomrecup = extras.get("nom").toString()
                prenomrecup = extras.get("prenom").toString()
                phonerecup = extras.get("phone").toString()
                findViewById<TextView>(R.id.editTextName).text = extras.get("nom").toString()
                findViewById<TextView>(R.id.editTextPrenom).text = extras.get("prenom").toString()
                findViewById<TextView>(R.id.editTextPhone).text = extras.get("phone").toString()
            }
        }
        val buttoncancel = findViewById<Button>(R.id.buttoncancel1)
        val buttonconfirm = findViewById<Button>(R.id.buttonconfirm1)

        // Envoie valeur changé Activité Secondaire vers Principale
        buttonconfirm.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (findViewById<EditText>(R.id.editTextName).text.toString() != ""
                    && findViewById<EditText>(R.id.editTextPrenom).text.toString() != ""
                    && findViewById<EditText>(R.id.editTextPhone).text.toString() != ""){
                    val intent = Intent(this@Activity2, MainActivity::class.java)
                    intent.putExtra("nom", findViewById<EditText>(R.id.editTextName).text.toString())
                    intent.putExtra("prenom", findViewById<EditText>(R.id.editTextPrenom).text.toString())
                    intent.putExtra("phone", findViewById<EditText>(R.id.editTextPhone).text.toString())
                    startActivity(intent)
                }

            }
        })

        //Envoie ancienne valeur vers Activité Principale
        buttoncancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Activity2, MainActivity::class.java)
                intent.putExtra("nom", nomrecup)
                intent.putExtra("prenom", prenomrecup)
                intent.putExtra("phone", phonerecup)
                startActivity(intent)
            }
        })
    }
}