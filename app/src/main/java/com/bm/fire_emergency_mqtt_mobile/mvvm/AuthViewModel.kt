package com.bm.fire_emergency_mqtt_mobile.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bm.fire_emergency_mqtt_mobile.dto.AccessToken
import com.bm.fire_emergency_mqtt_mobile.dto.LoginDto
import com.bm.fire_emergency_mqtt_mobile.dto.RegisterDto
import com.bm.fire_emergency_mqtt_mobile.models.User
import com.bm.fire_emergency_mqtt_mobile.services.AuthService
import com.bm.fire_emergency_mqtt_mobile.utilities.preferences.CustomSharedPreferences
import com.bm.fire_emergency_mqtt_mobile.utilities.preferences.SharedPreferencesToken
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.ResponseModel
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

    val result = MutableLiveData<SingleResponseModel<AccessToken>>()
    val isLoggedIn = MutableLiveData<SingleResponseModel<AccessToken>>()
    val logoutResult = MutableLiveData<Boolean>()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())


    fun register(registerDto: RegisterDto) {
        disposable.add(
            authService.register(registerDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SingleResponseModel<AccessToken>>() {
                    override fun onSuccess(t: SingleResponseModel<AccessToken>) {
                        result.value = t
                        SharedPreferencesToken.token = t.data!!.token
                        SharedPreferencesToken.userId = t.data!!.user.id
                        customSharedPreferences.saveToken(t.data)
                    }

                    override fun onError(e: Throwable) {
                        result.value = SingleResponseModel(null,false,e.message)
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
                        result.value = t
                        customSharedPreferences.saveToken(t.data!!)
                        SharedPreferencesToken.token = t.data.token
                        SharedPreferencesToken.userId = t.data.user.id
                    }

                    override fun onError(e: Throwable) {
                        result.value = SingleResponseModel(null,false,e.message)
                    }
                })
        )
    }

    fun isLoggedIn() {
        disposable.add(
            authService.isLoggedIn()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<SingleResponseModel<AccessToken>>() {
                    override fun onSuccess(t: SingleResponseModel<AccessToken>) {
                        if (t.success) {
                            isLoggedIn.value = t
                        }
                    }

                    override fun onError(e: Throwable) {
                        isLoggedIn.value = SingleResponseModel(null,false,e.message)
                    }

                })
        )
    }

    fun logOut() {
        logoutResult.value = true
        customSharedPreferences.removeToken()
        SharedPreferencesToken.token = ""
        SharedPreferencesToken.userId = null
    }


}