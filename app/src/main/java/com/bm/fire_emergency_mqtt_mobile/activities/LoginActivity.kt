package com.bm.fire_emergency_mqtt_mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityLoginBinding
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}