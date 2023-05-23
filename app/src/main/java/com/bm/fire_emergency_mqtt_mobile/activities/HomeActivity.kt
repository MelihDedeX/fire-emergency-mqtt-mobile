package com.bm.fire_emergency_mqtt_mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityHomeBinding
import com.bm.fire_emergency_mqtt_mobile.dto.ClientInfoDto
import com.bm.fire_emergency_mqtt_mobile.dto.CurrentUser
import com.bm.fire_emergency_mqtt_mobile.fragments.*
import com.bm.fire_emergency_mqtt_mobile.models.User
import com.bm.fire_emergency_mqtt_mobile.mvvm.AuthViewModel
import com.bm.fire_emergency_mqtt_mobile.mvvm.ClientInfoViewModel
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val clientInfoViewModel: ClientInfoViewModel by viewModels()

    private var currentFragment = R.id.home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            if (R.id.home == item.itemId && R.id.home != currentFragment) {
                setHomeFragment()
            } else if (R.id.settings == item.itemId && R.id.settings != currentFragment) {
                setSettingsFragment()

            } else if (R.id.statics == R.id.statics && R.id.statics != currentFragment) {
                setStaticsFragment()
            }
            true
        }
        updateClientInfo()
    }

    private fun updateClientInfo() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            val userId = CurrentUser.user.id;
            clientInfoViewModel.update(
                ClientInfoDto(0,0,"Berat","Samsung s22","Android",it.result, userId =userId )
            )
        }
    }

    private fun setHomeFragment() {
        if (currentFragment == R.id.statics) {
            val action = StaticsFragmentDirections.actionStaticsFragmentToHomeFragment()
            Navigation.findNavController(binding.fragmentContainerView).navigate(action)
        } else if (currentFragment == R.id.settings) {
            val action = SettingsFragmentDirections.actionSettingsFragmentToHomeFragment()
            Navigation.findNavController(binding.fragmentContainerView).navigate(action)
        }
        currentFragment = R.id.home


    }

    private fun setStaticsFragment() {
        if (currentFragment == R.id.home) {
            val action = HomeFragmentDirections.actionHomeFragmentToStaticsFragment()
            Navigation.findNavController(binding.fragmentContainerView).navigate(action)
        } else if (currentFragment == R.id.settings) {
            val action = SettingsFragmentDirections.actionSettingsFragmentToStaticsFragment()
            Navigation.findNavController(binding.fragmentContainerView).navigate(action)
        }
        currentFragment = R.id.statics
    }

    private fun setSettingsFragment() {
        if (currentFragment == R.id.home) {
            val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
            Navigation.findNavController(binding.fragmentContainerView).navigate(action)
        } else if (currentFragment == R.id.statics) {
            val action = StaticsFragmentDirections.actionStaticsFragmentToSettingsFragment()
            Navigation.findNavController(binding.fragmentContainerView).navigate(action)
        }
        currentFragment = R.id.settings

    }
}