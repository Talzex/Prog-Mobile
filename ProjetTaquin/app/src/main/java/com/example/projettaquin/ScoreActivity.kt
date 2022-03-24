package com.example.projettaquin

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList


class ScoreActivity :  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val scoretext = findViewById<TextView>(R.id.textScoreValue)
        val listview = findViewById<ListView>(R.id.listView)
        val prefsFacile = getSharedPreferences("leaderbordFacile", MODE_PRIVATE)
        val prefsMoyen = getSharedPreferences("leaderbordMoyen", MODE_PRIVATE)
        val prefsDifficile = getSharedPreferences("leaderbordDifficile", MODE_PRIVATE)
        val extras = intent.extras

        if(extras != null){
            if(extras.getString("minutes") != null && extras.getInt("level") != null && extras.getString("secondes") != null){
                val level = extras.getInt("level")
                var mins = extras.getString("minutes")
                val secondes = extras.getString("secondes")
                var listScore = ArrayList<String>()

                scoretext.text = "$mins : $secondes"
                when(level){
                    3->{
                        listScore = ListScore(prefsFacile,mins,secondes)
                    }

                    4->{
                        listScore = ListScore(prefsMoyen,mins,secondes)
                    }

                    5->{
                        listScore = ListScore(prefsDifficile,mins,secondes)
                    }
                }
                val listAdapter = ListViewAdapter(this, listScore)
                listview.adapter = listAdapter
            }
        }
    }

    private fun ListScore (pref : SharedPreferences, mins : String?, secondes : String?) : ArrayList<String> {
        val listScore : ArrayList<String>
        val editor = pref.edit()
        editor.putString(pref.all.size.toString(), "$mins:$secondes")
        editor.apply()
        val mapScore  =  pref.all as MutableMap<String,String>
        listScore = mapScore.values.toList() as ArrayList<String>
        listScore.sort()
        return  listScore
    }
}