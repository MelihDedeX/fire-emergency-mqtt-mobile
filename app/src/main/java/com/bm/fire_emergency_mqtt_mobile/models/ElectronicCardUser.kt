package com.bm.fire_emergency_mqtt_mobile.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class ElectronicCardUser(
    @SerializedName("id")
    val id: Number,
    @SerializedName("name")
    val name: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("card")
    val card: ElectronicCard,
    @SerializedName("created_at")
    val createdAt: Date
) : Entity {
}