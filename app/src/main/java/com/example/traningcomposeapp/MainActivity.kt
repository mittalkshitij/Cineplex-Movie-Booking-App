package com.example.traningcomposeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.traningcomposeapp.onboarding.ui.activity.OnBoardingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
    }
}