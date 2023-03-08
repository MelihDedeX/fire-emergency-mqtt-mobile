package com.bm.fire_emergency_mqtt_mobile.utilities.responses

import com.google.gson.annotations.SerializedName

open class ResponseModel(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?
) {
}