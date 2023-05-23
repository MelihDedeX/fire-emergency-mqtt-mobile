package com.bm.fire_emergency_mqtt_mobile.dto

import com.bm.fire_emergency_mqtt_mobile.models.User
import com.google.gson.annotations.SerializedName

class AccessToken(
    @SerializedName("user")
    val user : User,
    @SerializedName("token")
    val token : String
) {

}
