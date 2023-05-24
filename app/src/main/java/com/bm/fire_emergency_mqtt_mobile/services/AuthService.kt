package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.dto.AccessToken
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.dto.RegisterDto
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("auth/register")
    fun register(@Body registerDto: RegisterDto): Single<SingleResponseModel<AccessToken>>

    @POST("auth/login")
    fun login(@Body loginDto: LoginDto): Single<SingleResponseModel<AccessToken>>
    @GET("auth/isLoggedIn")
    fun isLoggedIn(): Single<SingleResponseModel<AccessToken>>
}