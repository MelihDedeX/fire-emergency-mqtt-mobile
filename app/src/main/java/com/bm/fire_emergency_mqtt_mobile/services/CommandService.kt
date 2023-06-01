package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.dto.CommandDto
import com.bm.fire_emergency_mqtt_mobile.models.Command
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.ResponseModel
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface CommandService {

    @POST("commands")
    fun add(@Body command: CommandDto): Single<ResponseModel>
}