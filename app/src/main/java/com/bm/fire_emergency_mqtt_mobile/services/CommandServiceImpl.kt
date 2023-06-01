package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.dto.CommandDto
import com.bm.fire_emergency_mqtt_mobile.models.Command
import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCardUser
import com.bm.fire_emergency_mqtt_mobile.models.Entity
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.ResponseModel
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single

class CommandServiceImpl : CommandService {

    private val api = api<CommandService, Entity>(false)

    override fun add(command: CommandDto): Single<ResponseModel> {
        return api.add(command);
    }

}