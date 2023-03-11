package com.bm.fire_emergency_mqtt_mobile.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class Location(
    @SerializedName("id")
    val id: Number,
    @SerializedName("latitude")
    val latitude: Long,
    @SerializedName("longitude")
    val longitude: Long,
    @SerializedName("card")
    val card: ElectronicCard,
    @SerializedName("created_at")
    val createdAt: Date
) : Entity {
}