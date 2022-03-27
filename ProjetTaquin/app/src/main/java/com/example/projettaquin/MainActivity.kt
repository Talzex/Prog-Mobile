package com.example.projettaquin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener {
    var level = -1
    var image = -1
    private val imagesID : Array<Int> = arrayOf(R.drawable.android,R.drawable.html5,R.drawable.fq20,R.drawable.cascade)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(
            this,
            R.array.difficulty,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val gridView = findViewById<GridView>(R.id.gridviewimage)
        gridView.onItemClickListener = this
        gridView.adapter = ImageAdapter(this,imagesID)

        val bouton = findViewById<Button>(R.id.button)
        bouton.setOnClickListener(this)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 != null) {
            level = p2;
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if(p0 != null){
           image = imagesID[p2]
        }
    }

    override fun onClick(p0: View?) {
        if(image != -1 && level != -1){
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("image",image)
            intent.putExtra("level",level)
            startActivity(intent)
        }
    }
}
