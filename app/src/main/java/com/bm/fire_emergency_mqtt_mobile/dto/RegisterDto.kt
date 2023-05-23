package com.bm.fire_emergency_mqtt_mobile.dto

import com.google.gson.annotations.SerializedName

class RegisterDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("username")
    val username:String
) {
}