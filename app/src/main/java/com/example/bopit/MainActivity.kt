package com.example.bopit

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGame = findViewById<Button>(R.id.buttonG)
        val buttonAbout = findViewById<Button>(R.id.buttonA)
        val buttonConfig = findViewById<Button>(R.id.buttonC)


        buttonGame.setOnClickListener{
            val intentAbout = Intent(this, GameActivity::class.java)
            startActivity(intentAbout)
        }
        buttonAbout.setOnClickListener{
            val intentAbout = Intent(this, AboutActivity::class.java)
            startActivity(intentAbout)
        }
        buttonConfig.setOnClickListener{
            val intentAbout = Intent(this, SettingsActivity::class.java)
            Log.d( "a","boton")
            startActivity(intentAbout)
        }
    }
}