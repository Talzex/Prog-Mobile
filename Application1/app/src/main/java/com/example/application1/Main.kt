package com.example.application1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        System.out.println("onCreate !!")
    }

    override fun onRestart() {
        super.onRestart()
        System.out.println("onRestart !!")
    }

    override fun onStop() {
        super.onStop()
        System.out.println("onStop !!")
    }

    override fun onResume() {
        super.onResume()
        System.out.println("onResume !!")
    }

    override fun onPause() {
        super.onPause()
        System.out.println("onPause !!")
    }

    override fun onStart() {
        super.onStart()
        System.out.println("onStart !!")
    }
}