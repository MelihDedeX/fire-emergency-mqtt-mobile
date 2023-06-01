package com.bm.fire_emergency_mqtt_mobile.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.adapters.CardViewAdapter
import com.bm.fire_emergency_mqtt_mobile.databinding.FragmentHomeBinding
import com.bm.fire_emergency_mqtt_mobile.dto.CurrentUser
import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCard
import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCardUser
import com.bm.fire_emergency_mqtt_mobile.models.User
import com.bm.fire_emergency_mqtt_mobile.mvvm.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: CardViewModel by viewModels()
    private lateinit var dataBinding: FragmentHomeBinding
    private val cards: ArrayList<ElectronicCardUser> = ArrayList()
    private lateinit var adapter: CardViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHomeBinding.inflate(inflater)

        getData()
        runRecyclerView()

        dataBinding.buttonAddDevice.setOnClickListener {
            openAddDeviceDialog()
        }

        viewModel.addResult.observe(viewLifecycleOwner) {
            getData()
        }

        return dataBinding.root

    }

    private fun setViewData() {
        dataBinding.totalDevice.text = cards.size.toString()
        dataBinding.userName.text = CurrentUser.user.fullName
    }


    private fun runRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        dataBinding.recyclerView.layoutManager = layoutManager
        adapter = CardViewAdapter(cards)
        dataBinding.recyclerView.adapter = adapter
    }

    private fun getData() {
        viewModel.findAllByUserID(CurrentUser.user.id, 0, 1000)
        viewModel.result.observe(viewLifecycleOwner) {
            if (it.success) {
                cards.clear()
                cards.addAll(it.data.content)
                adapter.notifyDataSetChanged()
                setViewData()
            }
        }
    }


    private fun openAddDeviceDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.card_add_dialog)

        val btnAdd = dialog.findViewById<Button>(R.id.addButton);
        val btnCancel = dialog.findViewById<Button>(R.id.cancelButton)
        val cardNameEditText = dialog.findViewById<EditText>(R.id.cardName)
        val cardUUIDEditText = dialog.findViewById<EditText>(R.id.cardUUID)



        btnAdd.setOnClickListener {
            val cardName = cardNameEditText.text.toString();
            val cardUUID = cardUUIDEditText.text.toString();
            if (cardName.isEmpty() || cardName.isBlank())
                Toast.makeText(context, "Card Name cannot be empty", Toast.LENGTH_LONG)
            else if (cardUUID.isEmpty() || cardUUID.isBlank())
                Toast.makeText(context, "Card ID cannot be empty", Toast.LENGTH_LONG)
            else {
                        val id = cardUUID.toInt()
                            viewModel.add(
                            ElectronicCardUser(
                                0,
                                cardName,
                                CurrentUser.user,
                                ElectronicCard(id, "", "", Date()),
                        Date()

                    )
                )
                dialog.cancel()

            }

        }
        btnCancel.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()
    }


}