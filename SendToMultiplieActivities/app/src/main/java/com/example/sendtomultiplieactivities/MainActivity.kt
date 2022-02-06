package com.example.sendtomultiplieactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modifuser = findViewById<Button>(R.id.button)

        val viewname = findViewById<TextView>(R.id.textViewName)
        val viewprenom = findViewById<TextView>(R.id.textViewPrenom)
        val viewphone = findViewById<TextView>(R.id.textViewPhone)


        val modifadd = findViewById<Button>(R.id.button2)

        val viewaddress = findViewById<TextView>(R.id.textViewAddress)
        val viewzipcode = findViewById<TextView>(R.id.textViewzipcode)
        val viewcity = findViewById<TextView>(R.id.textViewVille)

        //Récupération valeur de Activité Secondaire
        val extrasrecu = intent.extras
        if(extrasrecu!= null){
            //Récupération valeur de Activité Secondaire 2
            if (extrasrecu.getString("nom") != null
                && extrasrecu.getString("prenom")!= null
                && extrasrecu.getString("phone") != null){
                val nom = extrasrecu.getString("nom")
                val prenom = extrasrecu.getString("prenom")
                val phone = extrasrecu.getString("phone")

                viewname.text = nom
                viewprenom.text = prenom
                viewphone.text = phone

            }
            //Récupération valeur de Activité Secondaire 3
            if(extrasrecu.getString("address") != null
                && extrasrecu.getString("zipcode") != null
                && extrasrecu.getString("ville") != null){
                val address = extrasrecu.getString("address")
                val zipcode = extrasrecu.getString("zipcode")
                val ville = extrasrecu.getString("ville")



                viewaddress.text = address
                viewzipcode.text = zipcode
                viewcity.text = ville
            }
        }
        // Envoie valeur activité principale vers secondaire (User)
        modifuser.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, Activity2::class.java)
                intent.putExtra("nom", viewname.text.toString())
                intent.putExtra("prenom", viewprenom.text.toString())
                intent.putExtra("phone", viewphone.text.toString())
                startActivity(intent)
            }
        })
        // Envoie valeur activité principale vers secondaire (Addresse)
        modifadd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, Activity3::class.java)
                intent.putExtra("address", viewaddress.text.toString())
                intent.putExtra("zipcode", viewzipcode.text.toString())
                intent.putExtra("ville", viewcity.text.toString())
                startActivity(intent)
            }
        })
    }
}