package com.example.marvelapp.di

import com.example.marvelapp.data.core.data.module.NetworkModule
import com.example.marvelapp.data.datasource.remote.MarvelApi
import com.example.marvelapp.data.repository.MarvelRepositoryImpl
import com.example.marvelapp.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        marvelApi: MarvelApi,
    ): MarvelRepository {
        return MarvelRepositoryImpl(marvelApi)
    }
}