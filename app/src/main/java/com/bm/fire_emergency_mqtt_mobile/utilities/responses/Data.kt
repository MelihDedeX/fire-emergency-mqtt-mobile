package com.bm.fire_emergency_mqtt_mobile.utilities.responses

import com.google.gson.annotations.SerializedName

class Data<T>(
    @SerializedName("content")
    val content: List<T>,
    @SerializedName("totalPage")
    val totalPage: Number,
    @SerializedName("page")
    val page: Number,
    @SerializedName("size")
    val size: Number,
    @SerializedName("total")
    val total: Number

) {

}