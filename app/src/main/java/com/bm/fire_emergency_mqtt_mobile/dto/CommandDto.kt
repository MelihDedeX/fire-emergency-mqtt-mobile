package com.bm.fire_emergency_mqtt_mobile.dto

import com.bm.fire_emergency_mqtt_mobile.models.Command
import com.google.gson.annotations.SerializedName

class CommandDto(
    @SerializedName("cardId")
    val cardId: String,
    @SerializedName("command")
    val command: Command) {
}