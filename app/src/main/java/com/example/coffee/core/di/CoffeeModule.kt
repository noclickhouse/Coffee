package com.example.coffee.core.di

import com.example.coffee.presentation.authorization.AuthorizationValidator
import com.example.coffee.presentation.authorization.AuthorizationValidatorImpl
import com.example.coffee.data.CoffeeApi
import com.example.coffee.data.network.CoffeeCallAdapterFactory
import com.example.coffee.domain.coffee.CoffeeRepository
import com.example.coffee.data.network.coffee.CoffeeRepositoryImpl
import com.example.coffee.data.network.CoffeeService
import com.example.coffee.presentation.registration.RegistrationValidator
import com.example.coffee.presentation.registration.RegistrationValidatorImpl
import com.example.coffee.data.StorageService
import com.example.coffee.data.storage.StorageServiceImpl
import com.example.coffee.data.network.TokenInterceptor
import com.example.coffee.domain.token.TokenRepository
import com.example.coffee.data.token.TokenRepositoryImpl
import com.example.coffee.domain.user.UserRepository
import com.example.coffee.data.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoffeeModule {

    companion object {
        private const val BASE_URL = "http://147.78.66.203:3210/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(storageService: StorageService): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createClient(storageService))
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoffeeCallAdapterFactory())
        .build()

    private fun createClient(storageService: StorageService): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor(storageService))
        .build()

    @Provides
    @Singleton
    fun provideSharedPreferences(storageService: StorageServiceImpl): StorageService = storageService

    @Provides
    @Singleton
    fun provideCoffeeService(coffeeService: CoffeeService): CoffeeApi = coffeeService

    @Provides
    @Singleton
    fun provideTokenRepository(tokenRepository: TokenRepositoryImpl): TokenRepository = tokenRepository

    @Provides
    @Singleton
    fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository = userRepository

    @Provides
    @Singleton
    fun provideCoffeeRepository(coffeeRepository: CoffeeRepositoryImpl): CoffeeRepository = coffeeRepository

    @Provides
    @Singleton
    fun provideRegistrationValidator(registrationValidator: RegistrationValidatorImpl): RegistrationValidator = registrationValidator

    @Provides
    @Singleton
    fun provideAuthorizationValidator(authorizationValidator: AuthorizationValidatorImpl): AuthorizationValidator = authorizationValidator
}