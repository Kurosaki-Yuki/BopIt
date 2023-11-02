package com.example.bopit

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

private lateinit var mediaPlayer: MediaPlayer
private var plays = true

class GameActivity : AppCompatActivity() , GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    lateinit var gestureDetector : GestureDetectorCompat
    lateinit var myView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mediaPlayer = MediaPlayer.create(this, R.raw.benis_music)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        myView = findViewById<View>(R.id.view_id)

        gestureDetector = GestureDetectorCompat(this,this)
        gestureDetector.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
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

    override fun onDown(p0: MotionEvent): Boolean {

        return true
    }

    override fun onShowPress(p0: MotionEvent) {

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        myView.setBackgroundColor(Color.BLUE)

        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {

        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        myView.setBackgroundColor(Color.RED)
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        myView.setBackgroundColor(Color.YELLOW)

        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {

        return true
    }

    override fun onDoubleTap(p0: MotionEvent): Boolean {

        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        return true
    }


}



