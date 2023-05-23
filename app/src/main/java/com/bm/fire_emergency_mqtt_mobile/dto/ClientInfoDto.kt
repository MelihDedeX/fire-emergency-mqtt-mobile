package com.bm.fire_emergency_mqtt_mobile.dto

import com.google.gson.annotations.SerializedName


class ClientInfoDto(
    @SerializedName("latitude")
    private val latitude: Long = 0L,

    @SerializedName("longitude")
    private val longitude: Long = 0L,

    @SerializedName("phoneName")
    private val phoneName: String? = null,

    @SerializedName("phoneBrand")
    private val phoneBrand: String? = null,

    @SerializedName("operatingSystem")
    private val operatingSystem: String? = null,

    @SerializedName("token")
    private val token: String? = null,

    @SerializedName("userId")
    private val userId: Number) {
}