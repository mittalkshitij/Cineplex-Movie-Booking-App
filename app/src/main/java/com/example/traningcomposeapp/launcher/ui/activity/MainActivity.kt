package com.example.traningcomposeapp.launcher.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.traningcomposeapp.R
import com.example.traningcomposeapp.home.ui.activity.HomeActivity
import com.example.traningcomposeapp.launcher.ui.viewmodel.MainViewModel
import com.example.traningcomposeapp.onboarding.ui.activity.OnBoardingActivity
import com.example.traningcomposeapp.utils.ImageConfig
import com.example.traningcomposeapp.utils.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callImagesConfiguration()
    }

    private fun callImagesConfiguration() {
        lifecycleScope.launch {
            mainViewModel.callImagesConfiguration()

        }

        lifecycleScope.launch {
            mainViewModel.imageConfigurationResponse.collect {
                when (it) {
                    is Result.Success -> {
                        ImageConfig.imageConfig = it.data
                        val intent = Intent(this@MainActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }

                    is Result.Error -> {
                        val intent = Intent(this@MainActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }

                    Result.Loading -> {}
                }
            }
        }
    }
}