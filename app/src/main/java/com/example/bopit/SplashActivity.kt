package com.example.bopit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.preference.PreferenceManager
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    val tagLog ="SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val value = sharedPreferences.getString("splash_time", "2000")

        Log.i(tagLog,"Value of splash_time:" + value)

        val fadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade)
        val sizeUp = AnimationUtils.loadAnimation(applicationContext, R.anim.size)

        val animationSet =AnimationSet(true)
        animationSet.addAnimation(fadeIn)
        animationSet.addAnimation(sizeUp)

        val imageView = findViewById<ImageView>(R.id.bopIt)

        fadeIn.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation : Animation?){
            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        imageView.startAnimation(fadeIn)
        val seconds = value?.toLong()
        val delayMillis = seconds!!.toLong() // 2 seconds (adjust as needed)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the splash screen activity
        }, delayMillis)
    }
}