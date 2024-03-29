package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.dto.ClientInfoDto
import com.bm.fire_emergency_mqtt_mobile.models.ClientInfo
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
interface ClientInfoService {

    @POST("clientInfo")
    fun add(@Body clientInfo : ClientInfoDto) : Single<SingleResponseModel<ClientInfo>>
}