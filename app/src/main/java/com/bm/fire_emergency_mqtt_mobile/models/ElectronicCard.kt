package com.bm.fire_emergency_mqtt_mobile.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class ElectronicCard(
    @SerializedName("id")
    val id: Number,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("qr_code")
    val qrCode: String,
    @SerializedName("created_at")
    val createdAt: Date
) : Entity {
}