package com.bm.fire_emergency_mqtt_mobile.mvvm

import android.app.Application
import com.bm.fire_emergency_mqtt_mobile.dto.CommandDto
import com.bm.fire_emergency_mqtt_mobile.services.CommandService
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CommandViewModel @Inject constructor(
    private val commandService: CommandService,
    application: Application
) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()

    fun sendCommand(commandDto: CommandDto) {
        disposable.add(
            commandService.add(commandDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResponseModel>() {
                    override fun onSuccess(t: ResponseModel) {

                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }
}