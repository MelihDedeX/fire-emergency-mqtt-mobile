package com.bm.fire_emergency_mqtt_mobile.mvvm

import android.app.Application
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.dto.RegisterDto
import com.bm.fire_emergency_mqtt_mobile.services.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject
constructor(
    private val authService: AuthService, application: Application
) :
    BaseViewModel(application) {
    private val disposable = CompositeDisposable()


    fun register(registerDto: RegisterDto) {

    }

    fun login(loginDto: LoginDto) {

    }

}