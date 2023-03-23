package com.bm.fire_emergency_mqtt_mobile.dependency.resolver

import com.bm.fire_emergency_mqtt_mobile.services.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyInjectionModule {

    @Provides
    @Singleton
    fun authServiceProvider(): AuthService {
        return AuthServiceImpl()
    }

    @Provides
    @Singleton
    fun cardLocationServiceProvider(): CardLocationService {
        return CardLocationServiceImpl()
    }

    @Provides
    @Singleton
    fun electronicCardServiceProvider(): ElectronicCardService {
        return ElectronicCardServiceImpl()
    }

    @Provides
    @Singleton
    fun electronicCardUserProvider(): ElectronicCardUserService {
        return ElectronicCardUserServiceImpl()
    }

    @Provides
    @Singleton
    fun userServiceProvider(): UserService {
        return UserServiceImpl()
    }


}