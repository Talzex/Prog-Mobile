package com.example.projettaquin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class ScoreActivity :  AppCompatActivity(),View.OnClickListener {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val scoretext = findViewById<TextView>(R.id.textScoreValue)
        val listview = findViewById<ListView>(R.id.listView)
        val bouton = findViewById<Button>(R.id.buttontoMenu)
        bouton.setOnClickListener(this)
        val prefsFacile = getSharedPreferences("leaderbordFacile", MODE_PRIVATE)
        val prefsMoyen = getSharedPreferences("leaderbordMoyen", MODE_PRIVATE)
        val prefsDifficile = getSharedPreferences("leaderbordDifficile", MODE_PRIVATE)
        val extras = intent.extras

        if(extras != null){
            if(extras.getString("minutes") != null && extras.getInt("level") != null && extras.getString("secondes") != null){
                val level = extras.getInt("level")
                val mins = extras.getString("minutes")
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
                        listScore = ListScore(prefsDifficile,mins, secondes)
                    }
                }
                val listAdapter = ListViewAdapter(this, listScore)
                listview.adapter = listAdapter
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun ListScore (pref : SharedPreferences, mins : String?, secondes : String?) : ArrayList<String> {
        val listScore = ArrayList<String>()
        val editor = pref.edit()
        editor.putString("$pref.all.size", "$mins:$secondes")
        editor.apply()
        val allPrefs: Map<String, *> = pref.all
        allPrefs.forEach {
            listScore.add(it.value.toString())
        }
        listScore.sort()
        listScore.stream().limit(10)
        return listScore
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this@ScoreActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
