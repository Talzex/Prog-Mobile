package com.example.projettaquin

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.AdapterView
import android.widget.Chronometer
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    var level = -1;
    var image = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val simpleChronometer = findViewById<View>(R.id.simpleChronometer) as Chronometer // initiate a chronometer
        simpleChronometer.start();

        val extras = intent.extras
        if(extras != null){
            if(extras.getInt("image") != null && extras.getInt("level") != null){

                image = extras.getInt("image")
                val img : Array<Int> = arrayOf(image)
                val gridView = findViewById<GridView>(R.id.gridiew)
                gridView.onItemClickListener = this
                gridView.adapter = ImageAdapter(this,img)

                level = extras.getInt("level")
                when(level){
                    0 -> {
                        gridView.columnWidth = 50

                    }
                    1 ->{
                        gridView.columnWidth = 25

                    }
                    2 -> {
                        gridView.columnWidth = 12

                    }
                }
            }
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }
}