package com.example.bopit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAbout = findViewById<Button>(R.id.button)

        buttonAbout.setOnClickListener{
            val intentAbout = Intent(this, AboutActivity::class.java)
            startActivity(intentAbout)
        }
    }



}