package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.models.CardLocation
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single

class CardLocationServiceImpl : CardLocationService {

    private val api = api<CardLocationService, CardLocation>()

    override fun findLocationByCardId(cardId: Number): Single<SingleResponseModel<CardLocation>> {
        return api.findLocationByCardId(cardId)
    }
}