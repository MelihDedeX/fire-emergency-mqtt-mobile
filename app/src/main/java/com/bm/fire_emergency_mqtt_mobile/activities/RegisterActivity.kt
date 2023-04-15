package com.bm.fire_emergency_mqtt_mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityLoginBinding
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityMainBinding
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}