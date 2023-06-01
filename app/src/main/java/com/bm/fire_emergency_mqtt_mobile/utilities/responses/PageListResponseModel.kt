package com.bm.fire_emergency_mqtt_mobile.utilities.responses

import com.google.gson.annotations.SerializedName

open class PageListResponseModel<T>(

    @SerializedName("data")
    val data: Data<T>,
    success: Boolean,
    message: String?
) : ResponseModel(success, message) {

}