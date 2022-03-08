package com.parsadehghan.data.di

import android.content.Context
import com.parsadehghan.data.BuildConfig
import com.parsadehghan.data.remote.OpenWeatherApiService
import com.parsadehghan.domain.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFoursquareService(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): OpenWeatherApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(gsonConverter)
            .client(okHttpClient)
            .build()
        return retrofit.create(OpenWeatherApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}