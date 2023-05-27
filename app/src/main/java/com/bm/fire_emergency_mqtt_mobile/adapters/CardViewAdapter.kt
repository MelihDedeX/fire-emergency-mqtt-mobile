package com.bm.fire_emergency_mqtt_mobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.databinding.DeviceItemBinding
import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCardUser

class CardViewAdapter(private val cards: List<ElectronicCardUser>) :
    RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {

    class CardViewHolder(var view: DeviceItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<DeviceItemBinding>(inflater, R.layout.device_item,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.view.deviceName.text = cards[position].name
    }
}