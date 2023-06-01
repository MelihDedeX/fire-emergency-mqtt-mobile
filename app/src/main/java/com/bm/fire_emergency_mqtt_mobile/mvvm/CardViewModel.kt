package com.bm.fire_emergency_mqtt_mobile.mvvm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bm.fire_emergency_mqtt_mobile.dto.AccessToken
import com.bm.fire_emergency_mqtt_mobile.models.ElectronicCardUser
import com.bm.fire_emergency_mqtt_mobile.services.ElectronicCardService
import com.bm.fire_emergency_mqtt_mobile.services.ElectronicCardUserService
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.PageListResponseModel
import com.bm.fire_emergency_mqtt_mobile.utilities.responses.SingleResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardService: ElectronicCardUserService, application: Application
) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    val result = MutableLiveData<PageListResponseModel<ElectronicCardUser>>()
    val addResult = MutableLiveData<Boolean>()

    fun findAllByUserID(userId: Number, page: Number, size: Number) {
        disposable.add(
            cardService.findAllByUserId(userId, page, size)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<PageListResponseModel<ElectronicCardUser>>() {
                    override fun onSuccess(t: PageListResponseModel<ElectronicCardUser>) {
                        result.value = t
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    fun add(electronicCardUser: ElectronicCardUser) {
        disposable.add(
            cardService.add(electronicCardUser)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SingleResponseModel<ElectronicCardUser>>() {
                    override fun onSuccess(t: SingleResponseModel<ElectronicCardUser>) {
                        addResult.value = true
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

}