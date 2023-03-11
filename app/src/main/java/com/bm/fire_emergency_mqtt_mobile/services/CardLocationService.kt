package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.models.CardLocation
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CardLocationService {
    @GET
    fun findLocationByCardId(@Path("cardId") cardId: Number): Single<SingleResponseModel<CardLocation>>
}