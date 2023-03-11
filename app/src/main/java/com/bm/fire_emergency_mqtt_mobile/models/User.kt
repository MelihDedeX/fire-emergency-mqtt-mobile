package com.bm.fire_emergency_mqtt_mobile.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class User(
    @SerializedName("id")
    val id: Number,
    @SerializedName("email")
    val email: String,
    @SerializedName("created_at")
    val createdAt: Date
) : Entity {
}