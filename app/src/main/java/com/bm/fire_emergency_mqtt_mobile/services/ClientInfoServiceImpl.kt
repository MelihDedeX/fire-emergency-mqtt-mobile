package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.dto.ClientInfoDto
import com.bm.fire_emergency_mqtt_mobile.models.ClientInfo
import com.bm.fire_emergency_mqtt_mobile.models.Entity
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single

class ClientInfoServiceImpl : ClientInfoService {

    private val api = api<ClientInfoService, Entity>(false)

    override fun add(clientInfo: ClientInfoDto): Single<SingleResponseModel<ClientInfo>> {
        return api.add(clientInfo)
    }
}