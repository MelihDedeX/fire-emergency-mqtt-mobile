package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCardUser
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.PageListResponseModel
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single

class ElectronicCardUserServiceImpl : ElectronicCardUserService {

    private val api = api<ElectronicCardUserService, ElectronicCardUser>(false)
    override fun add(card: ElectronicCardUser): Single<SingleResponseModel<ElectronicCardUser>> {
        return api.add(card)
    }



    override fun findAllByUserId(
        userId: Number,
        page: Number,
        pageSize: Number
    ): Single<PageListResponseModel<ElectronicCardUser>> {
        return api.findAllByUserId(userId, page, pageSize)
    }
}