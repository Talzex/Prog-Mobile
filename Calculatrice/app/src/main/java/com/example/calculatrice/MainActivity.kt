package com.example.calculatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonplus = findViewById<Button>(R.id.button_plus)
        val buttonmoins = findViewById<Button>(R.id.button_moins)
        val buttonfois = findViewById<Button>(R.id.button_fois)
        val buttondiv = findViewById<Button>(R.id.button_diviser)
        val resultat = findViewById<TextView>(R.id.textView2)
        val nb1 = findViewById<EditText>(R.id.editTextNumber)
        val nb2 = findViewById<EditText>(R.id.editTextNumber2)
        buttonplus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val sum = nb1.text.toString().toInt() + nb2.text.toString().toInt()
                resultat.setText(sum.toString())
            }
        })
        buttonmoins.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val sum = nb1.text.toString().toInt() - nb2.text.toString().toInt()
                resultat.setText(sum.toString())
            }
        })
        buttonfois.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val sum = nb1.text.toString().toInt() * nb2.text.toString().toInt()
                resultat.setText(sum.toString())
            }
        })
        buttondiv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (nb2.text.toString().toInt() != 0) {
                    val sum = nb1.text.toString().toInt() / nb2.text.toString().toInt()
                    resultat.setText(sum.toString())
                } else {
                    resultat.setText("Division par 0 impossible")
                }

            }
        })
    }

}