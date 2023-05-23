package com.bm.fire_emergency_mqtt_mobile.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityMainBinding
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.firebase.FirebaseMessageReceiver
import com.bm.fire_emergency_mqtt_mobile.mvvm.AuthViewModel
import com.bm.fire_emergency_mqtt_mobile.utilities.preferences.CustomSharedPreferences
import com.bm.fire_emergency_mqtt_mobile.utilities.preferences.SharedPreferencesToken
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : AuthViewModel by viewModels()
    private lateinit var customSharedPreferences  : CustomSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customSharedPreferences  = CustomSharedPreferences(application)
        SharedPreferencesToken.token = customSharedPreferences.getToken()
        SharedPreferencesToken.userId = customSharedPreferences.getUserId()

        binding.login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.register.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }



    }
}