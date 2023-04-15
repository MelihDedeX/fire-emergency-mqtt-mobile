package com.bm.fire_emergency_mqtt_mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityHomeBinding
import com.bm.fire_emergency_mqtt_mobile.fragments.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
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