package com.example.bopit

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

private lateinit var yippiePlayer: MediaPlayer
private lateinit var screamPlayer: MediaPlayer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGame = findViewById<Button>(R.id.buttonG)
        val buttonAbout = findViewById<Button>(R.id.buttonA)
        val buttonConfig = findViewById<Button>(R.id.buttonC)

        val buttonStop = findViewById<Button>(R.id.stopButton)
        val buttonYippie = findViewById<Button>(R.id.yippieButton)
        val buttonScream = findViewById<Button>(R.id.screamButton)



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

        /*buttonStop.setOnClickListener {
            if(plays){
                plays = false
                mediaPlayer.pause()
            }else if(!plays){
                plays = true
                mediaPlayer.start()
            }

        }*/

        buttonYippie.setOnClickListener {
            yippiePlayer = MediaPlayer.create(this, R.raw.yippee_tbh)
            yippiePlayer.start()
        }

        buttonScream.setOnClickListener {
            screamPlayer = MediaPlayer.create(this, R.raw.tbh_shatter)
            screamPlayer.start()
        }
    }


}