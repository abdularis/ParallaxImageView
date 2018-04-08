package com.github.abdularis.pivsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onVerticalParallaxClick(v : View) {
        val i = Intent(this, VerticalParallaxActivity::class.java)
        startActivity(i)
    }

    fun onHorizontalScaleClick(v : View) {
        val i = Intent(this, HorizontalParallaxActivity::class.java)
        startActivity(i)
    }
}
