package com.example.bopit

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.core.os.postDelayed
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs
import kotlin.random.Random

private lateinit var mediaPlayer: MediaPlayer
private lateinit var yippiePlayer: MediaPlayer
private lateinit var screamPlayer: MediaPlayer
private var plays = true

class GameActivity : AppCompatActivity() , SensorEventListener , GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    lateinit var gestureDetector : GestureDetectorCompat
    lateinit var myView: View
    lateinit var text: TextView
    lateinit var pText: TextView

    var points = 0
    var pointT = points.toString()
    var rand = 0
    val handler = Handler(Looper.getMainLooper())
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var prevX = 0f

    var click: Boolean = false
    var swipe: Boolean = false
    var holds: Boolean = false
    var shake: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        text = findViewById(R.id.gameText)
        pText = findViewById(R.id.pointsText)
        pText.setText(pointT)

        mediaPlayer = MediaPlayer.create(this, R.raw.benis_music)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        myView = findViewById(R.id.view_id)

        gestureDetector = GestureDetectorCompat(this,this)
        gestureDetector.setOnDoubleTapListener(this)

        handler.post(activitySelecter)
    }

    val activitySelecter = Runnable{
        rand = Random.nextInt(0,4)

        sensorManager.unregisterListener(this)

        when(rand){
            0->{
                click = true
                myView.setBackgroundColor(Color.BLUE)
                text.setText(R.string.click)
                handler.postDelayed(gameOver, 3000)
            }
            1->{
                swipe = true
                myView.setBackgroundColor(Color.YELLOW)
                text.setText(R.string.swipe)
                handler.postDelayed(gameOver, 3000)
            }
            2->{
                holds = true
                myView.setBackgroundColor(Color.RED)
                text.setText(R.string.holds)
                handler.postDelayed(gameOver, 3000)
            }
            3->{
                shake = true
                myView.setBackgroundColor(Color.GREEN)
                text.setText(R.string.shake)
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
                handler.postDelayed(gameOver, 3000)
            }
        }
    }

    val gameOver = Runnable{
        if(click || swipe || holds || shake){
            screamPlayer = MediaPlayer.create(this, R.raw.tbh_shatter)
            screamPlayer.start()
            val intentLose = Intent(this, MainActivity::class.java)
            startActivity(intentLose)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            if(shake){
                if(abs(abs(prevX) - x) > 20f){
                    yippiePlayer = MediaPlayer.create(this, R.raw.yippee_tbh)
                    yippiePlayer.start()
                    shake = false
                    points+=100
                    pointT = points.toString()
                    pText.setText(pointT)
                    handler.post(activitySelecter)
                }
            }
            prevX = x
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onPause() {
        super.onPause()
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        if(!mediaPlayer.isPlaying && plays){
            mediaPlayer.start()
        }
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onDown(p0: MotionEvent): Boolean {

        return true
    }

    override fun onShowPress(p0: MotionEvent) {

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {

        if(click){
            yippiePlayer = MediaPlayer.create(this, R.raw.yippee_tbh)
            yippiePlayer.start()
            points+=100
            pointT = points.toString()
            pText.setText(pointT)
            click = false
            handler.post(activitySelecter)
        }

        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {

        return true
    }

    override fun onLongPress(p0: MotionEvent) {

        if(holds){
            yippiePlayer = MediaPlayer.create(this, R.raw.yippee_tbh)
            yippiePlayer.start()
            points+=100
            pointT = points.toString()
            pText.setText(pointT)
            holds = false
            handler.post(activitySelecter)
        }
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {

        if(swipe){
            yippiePlayer = MediaPlayer.create(this, R.raw.yippee_tbh)
            yippiePlayer.start()
            points+=100
            pointT = points.toString()
            pText.setText(pointT)
            swipe = false
            handler.post(activitySelecter)
        }

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



