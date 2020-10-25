package com.graveno.alphalab.covidtracker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.graveno.alphalab.covidtracker.R

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}