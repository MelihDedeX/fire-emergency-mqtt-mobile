package com.bm.fire_emergency_mqtt_mobile.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.ActivityHomeBinding
import com.bm.fire_emergency_mqtt_mobile.dto.ClientInfoDto
import com.bm.fire_emergency_mqtt_mobile.dto.CommandDto
import com.bm.fire_emergency_mqtt_mobile.dto.CurrentUser
import com.bm.fire_emergency_mqtt_mobile.firebase.DataType
import com.bm.fire_emergency_mqtt_mobile.fragments.*
import com.bm.fire_emergency_mqtt_mobile.models.Command
import com.bm.fire_emergency_mqtt_mobile.mvvm.ClientInfoViewModel
import com.bm.fire_emergency_mqtt_mobile.mvvm.CommandViewModel
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val clientInfoViewModel: ClientInfoViewModel by viewModels()
    private val commandViewModel: CommandViewModel by viewModels()

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

    override fun onResume() {
        isUrgent(
            intent?.getStringExtra("dataType"),
            intent?.getStringExtra("urgentData"),
            intent?.getStringExtra("cardId")
        )
        super.onResume()
    }

    private fun updateClientInfo() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            val userId = CurrentUser.user.id;
            clientInfoViewModel.update(
                ClientInfoDto(0, 0, "Berat", "Samsung s22", "Android", it.result, userId = userId)
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

    private fun isUrgent(dataType: String?, message: String?, cardId: String?) {
        if (dataType?.equals(DataType.URGENT.name) == true) {
            showUrgentDialog(message, cardId)
        }
    }

    private fun showUrgentDialog(message: String?, cardId: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.urgent_layout)
        dialog.getWindow()?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        );
        val btnRun = dialog.findViewById<Button>(R.id.btnRun);
        val btnIgnore = dialog.findViewById<Button>(R.id.btnIgnore)
        val text = dialog.findViewById<TextView>(R.id.message)
        text.text = "${message}. Do you want to run water engines?"

        btnRun.setOnClickListener {
            commandViewModel.sendCommand(
                CommandDto(cardId!!, Command.RUN)
            )
        }

        btnIgnore.setOnClickListener {

            dialog.cancel()
        }

        dialog.show()
    }


}