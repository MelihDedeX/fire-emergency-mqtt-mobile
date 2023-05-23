package com.bm.fire_emergency_mqtt_mobile.mvvm

import android.app.Application
import com.bm.fire_emergency_mqtt_mobile.dto.AccessToken
import com.bm.fire_emergency_mqtt_mobile.dto.ClientInfoDto
import com.bm.fire_emergency_mqtt_mobile.models.ClientInfo
import com.bm.fire_emergency_mqtt_mobile.services.ClientInfoService
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application, private val service : ClientInfoService )
    : BaseViewModel(application) {

    private val disposable = CompositeDisposable()

    fun updateClientInfo(info : ClientInfoDto) {
        disposable.add(
            service.add(info)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SingleResponseModel<ClientInfo>>() {
                    override fun onSuccess(t: SingleResponseModel<ClientInfo>) {

                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }
}