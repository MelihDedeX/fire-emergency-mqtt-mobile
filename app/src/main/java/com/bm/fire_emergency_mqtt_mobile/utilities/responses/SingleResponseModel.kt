package com.bm.fire_emergency_mqtt_mobile.utilities.responses

import com.google.gson.annotations.SerializedName

open class SingleResponseModel<T>(
    @SerializedName("data")
    val data: T?,
    success: Boolean,
    message: String?
) : ResponseModel(success, message) {


}