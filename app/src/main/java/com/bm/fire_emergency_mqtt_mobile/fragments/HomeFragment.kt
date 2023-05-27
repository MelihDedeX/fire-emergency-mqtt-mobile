package com.bm.fire_emergency_mqtt_mobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.adapters.CardViewAdapter
import com.bm.fire_emergency_mqtt_mobile.databinding.FragmentHomeBinding
import com.bm.fire_emergency_mqtt_mobile.dto.CurrentUser
import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCardUser

class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var cards: ArrayList<ElectronicCardUser>
    private lateinit var adapter: CardViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHomeBinding.inflate(inflater)
        runRecyclerView()
        return dataBinding.root

    }
    private fun setViewData() {
        dataBinding.totalDevice.text = cards.size.toString()
        dataBinding.userName.text = CurrentUser.user.fullName
    }
    private fun runRecyclerView() {
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        dataBinding.recyclerView.layoutManager = layoutManager
        adapter = CardViewAdapter(getData())
        dataBinding.recyclerView.adapter = adapter
    }

    private fun getData(): List<ElectronicCardUser> {
        cards = ArrayList()
        cards.add(ElectronicCardUser(1, "Legolas", null, null, null))
        cards.add(ElectronicCardUser(2, "Frodo", null, null, null))
        cards.add(ElectronicCardUser(3, "Aragorn", null, null, null))
        cards.add(ElectronicCardUser(4, "Gandalf", null, null, null))
        cards.add(ElectronicCardUser(5, "Messi", null, null, null))
        cards.add(ElectronicCardUser(6, "Bay Kemal", null, null, null))
        cards.add(ElectronicCardUser(7, "Feanor", null, null, null))
        cards.add(ElectronicCardUser(8, "Sauron", null, null, null))
        setViewData();
        return cards;
    }


}