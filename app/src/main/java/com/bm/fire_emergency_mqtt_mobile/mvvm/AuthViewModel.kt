package com.bm.fire_emergency_mqtt_mobile.mvvm

import android.app.Application
import com.bm.fire_emergency_mqtt_mobile.dto.AccessToken
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.dto.RegisterDto
import com.bm.fire_emergency_mqtt_mobile.services.AuthService
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AuthViewModel @Inject
constructor(
    private val authService: AuthService, application: Application
) :
    BaseViewModel(application) {
    private val disposable = CompositeDisposable()


    fun register(registerDto: RegisterDto) {
        disposable.add(
            authService.register(registerDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SingleResponseModel<AccessToken>>() {
                    override fun onSuccess(t: SingleResponseModel<AccessToken>) {
                        println(t)
                    }

                    override fun onError(e: Throwable) {
                        println(e)
                    }

                })
        )
    }

    fun login(loginDto: LoginDto) {
        disposable.add(
            authService.login(loginDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SingleResponseModel<AccessToken>>() {
                    override fun onSuccess(t: SingleResponseModel<AccessToken>) {
                        println(t)
                    }

                    override fun onError(e: Throwable) {
                        println(e)

                    }

                })
        )
    }

}