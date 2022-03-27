package com.example.projettaquin

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Chronometer
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import kotlin.math.min


class GameActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    final val RAMDOM_MOVEMENT = 100
    var size = -1
    var DrawableId = -1;
    var tilesArray  = ArrayList<Tile>()
    lateinit var imgAdapter : ImageAdapter2
    lateinit var simpleChronometer : Chronometer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        play()
    }

    private fun play() {
        simpleChronometer = findViewById<View>(R.id.simpleChronometer) as Chronometer
        simpleChronometer.format = getString(R.string.Duree) + " %s"
        simpleChronometer.start()

        val gridView = findViewById<GridView>(R.id.gridview)
        gridView.onItemClickListener = this

        val extras = intent.extras
        if(extras != null){
            if(extras.getInt("image") != null && extras.getInt("level") != null){
                DrawableId = extras.getInt("image")
                val imgBitmap = resources.getDrawable(DrawableId,theme).toBitmap()
                when(extras.getInt("level")){
                    0 -> {
                        size = 3
                        gridView.numColumns = 3
                        DecoupeImage(imgBitmap,3);
                        imgAdapter = ImageAdapter2(this,tilesArray,3)
                        gridView.adapter = imgAdapter
                    }
                    1 ->{
                        size = 4
                        gridView.numColumns = 4
                        DecoupeImage(imgBitmap,4);
                        imgAdapter = ImageAdapter2(this,tilesArray,4)
                        gridView.adapter = imgAdapter

                    }
                    2 -> {
                        size = 5
                        gridView.numColumns = 5
                        DecoupeImage(imgBitmap,5);
                        imgAdapter = ImageAdapter2(this,tilesArray,5)
                        gridView.adapter = imgAdapter
                    }
                }
                RandomPosition(size)
            }
        }
    }

    private fun RandomPosition(size : Int) {
        var i = 0
        while(i < RAMDOM_MOVEMENT){
            if(imgAdapter.changePosition((0 until (size*size)).random())){
                i++
            }
        }
    }

    private fun DecoupeImage(img : Bitmap, level : Int) {
        val boutWidth = img.width/level
        val boutHeight = img.height/level
        for(i in 0 until level){
            for(j in 0 until level){
                val unBout = Bitmap.createBitmap(img, boutHeight * j, boutWidth * i, boutWidth,boutHeight)
                val tiles = Tile(i,j,unBout,i*size+j)
                tilesArray.add(tiles)
            }
        }
        tilesArray[tilesArray.size-1].image = null
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        imgAdapter.changePosition(p2)
        if(checkWin()){
            simpleChronometer.stop()
            val timetext = simpleChronometer.text
            val time = timetext.split(getString(R.string.Duree))
            val timesplit = time[1].split(":")

            val intent = Intent(this@GameActivity, ScoreActivity::class.java)
            intent.putExtra("minutes",timesplit[0])
            intent.putExtra("secondes",timesplit[1])
            intent.putExtra("level",size)
            startActivity(intent)
        }
    }

    fun checkWin() : Boolean{
        var ordonee = true
        var i = 0
        imgAdapter.tilesArrayList.forEach {
            if(i != it.numero){
                ordonee = false
            }
            i++;
        }
        return ordonee;
    }
}