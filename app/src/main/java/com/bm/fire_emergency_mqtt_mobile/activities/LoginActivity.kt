package com.bm.fire_emergency_mqtt_mobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityLoginBinding
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityMainBinding
import com.bm.fire_emergency_mqtt_mobile.dto.CurrentUser
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.mvvm.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()

        authViewModel.login(LoginDto(email, password))

        if (!email.contains("@") && !email.contains(".com")) {
            Toast.makeText(this, "This is not an email address", Toast.LENGTH_LONG).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(this, "Password must be six character", Toast.LENGTH_LONG).show()
            return
        }
        authViewModel.login(LoginDto(email, password))
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