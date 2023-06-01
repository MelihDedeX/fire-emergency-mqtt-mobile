package com.bm.fire_emergency_mqtt_mobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityLoginBinding
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityMainBinding
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityRegisterBinding
import com.bm.fire_emergency_mqtt_mobile.dto.CurrentUser
import com.bm.fire_emergency_mqtt_mobile.dto.RegisterDto
import com.bm.fire_emergency_mqtt_mobile.mvvm.AuthViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var backButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton2.setOnClickListener {
            register()
        }
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }


    private fun register() {
        val email = binding.registerEmailText.text.toString()
        val fullName = binding.registerNameText.text.toString()
        val password = binding.registerPasswordText.text.toString()
        val username = binding.registerUserNameText.text.toString()

        if (!email.contains("@") && !email.contains(".com")) {
            Toast.makeText(this, "This is not an email address", Toast.LENGTH_LONG).show()
            return
        }
        if (fullName.isBlank() || fullName.isEmpty()) {
            Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_LONG).show()
            return
        }
        if (username.isBlank() || username.isEmpty()) {
            Toast.makeText(this, "User Name cannot be blank", Toast.LENGTH_LONG).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(this, "Password must be six character", Toast.LENGTH_LONG).show()
            return
        }

        authViewModel.register(RegisterDto(email, password, fullName, username))

        authViewModel.result.observe(this) {
            if (it.success) {
                CurrentUser.user = it.data!!.user
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}