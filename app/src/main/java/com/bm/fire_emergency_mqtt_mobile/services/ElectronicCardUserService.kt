package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCardUser
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.PageListResponseModel
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ElectronicCardUserService  {

    @POST("electronicCardUsers")
    fun add(@Body card: ElectronicCardUser): Single<SingleResponseModel<ElectronicCardUser>>

    @GET("electronicCardUsers")
    fun findAllByUserId(
        @Query("userId") userId: Number,
        @Query("page") page: Number,
        @Query("pageSize") pageSize: Number
    ): Single<PageListResponseModel<ElectronicCardUser>>


}