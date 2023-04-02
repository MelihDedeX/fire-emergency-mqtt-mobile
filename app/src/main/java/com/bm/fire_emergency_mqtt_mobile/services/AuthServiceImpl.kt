package com.bm.fire_emergency_mqtt_mobile.services

import com.bm.fire_emergency_mqtt_mobile.dto.AccessToken
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.dto.RegisterDto
import com.bm.fire_emergency_mqtt_mobile.models.Entity
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import io.reactivex.Single

class AuthServiceImpl : AuthService {

    private val api = api<AuthService, Entity>(false)
    override fun register(registerDto: RegisterDto): Single<SingleResponseModel<AccessToken>> {
        return api.register(registerDto)
    }

    override fun login(loginDto: LoginDto): Single<SingleResponseModel<AccessToken>> {
        return api.login(loginDto)
    }
}