package com.bm.fire_emergency_mqtt_mobile.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityMainBinding
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.mvvm.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

    }
}