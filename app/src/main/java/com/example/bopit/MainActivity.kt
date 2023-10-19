package com.example.bopit

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

private lateinit var mediaPlayer: MediaPlayer
private lateinit var yippiePlayer: MediaPlayer
private lateinit var screamPlayer: MediaPlayer
private var plays = true


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAbout = findViewById<Button>(R.id.buttonA)
        val buttonConfig = findViewById<Button>(R.id.buttonC)

        val buttonStop = findViewById<Button>(R.id.stopButton)
        val buttonYippie = findViewById<Button>(R.id.yippieButton)
        val buttonScream = findViewById<Button>(R.id.screamButton)

        mediaPlayer = MediaPlayer.create(this, R.raw.benis_music)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        buttonAbout.setOnClickListener{
            val intentAbout = Intent(this, AboutActivity::class.java)
            startActivity(intentAbout)
        }
        buttonConfig.setOnClickListener{
            val intentAbout = Intent(this, SettingsActivity::class.java)
            Log.d( "a","boton")
            startActivity(intentAbout)
        }

        buttonStop.setOnClickListener {
            if(plays){
                plays = false
                mediaPlayer.pause()
            }else if(!plays){
                plays = true
                mediaPlayer.start()
            }

        }

        buttonYippie.setOnClickListener {
            yippiePlayer = MediaPlayer.create(this, R.raw.yippee_tbh)
            yippiePlayer.start()
        }

        buttonScream.setOnClickListener {
            screamPlayer = MediaPlayer.create(this, R.raw.tbh_shatter)
            screamPlayer.start()
        }
    }
    override fun onPause() {
        super.onPause()
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if(!mediaPlayer.isPlaying && plays){
            mediaPlayer.start()
        }
    }

}