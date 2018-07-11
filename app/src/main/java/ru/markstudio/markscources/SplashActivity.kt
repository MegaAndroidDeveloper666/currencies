package ru.markstudio.markscources

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    companion object {
        val SPLASH_TAG = "Splash"
        val SPLASH_DELAY: Long = 1500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        Timer(SPLASH_TAG, false).schedule(SPLASH_DELAY) {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}
