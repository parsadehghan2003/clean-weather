package com.parsadehghan.data.di

import android.content.Context
import com.parsadehghan.data.ErrorHandler
import com.parsadehghan.data.RepositoryImpl
import com.parsadehghan.data.local.LocalDataSource
import com.parsadehghan.data.local.LocalDataSourceImpl
import com.parsadehghan.data.local.PreferencesHelper
import com.parsadehghan.data.remote.OpenWeatherApiService
import com.parsadehghan.data.remote.RemoteDataSource
import com.parsadehghan.data.remote.RemoteDataSourceImpl
import com.parsadehghan.inteactor.Repository
import com.parsadehghan.domain.exceptions.IErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl,
        localDataSourceImpl: LocalDataSourceImpl
    ): Repository {
        return RepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }
//    @Provides
//    @Singleton
//    fun providesRealmDatabase(
//        @ApplicationContext context: Context
//    ): Realm {
//        Realm.init(context)
//        val realmConfiguration = RealmConfiguration
//            .Builder()
//            .name("interactor")
//            .build()
//        Realm.setDefaultConfiguration(realmConfiguration)
//        return Realm.getDefaultInstance()
//    }
    @Singleton
    @Provides
    fun provideLocalDataSource(
        preferencesHelper: PreferencesHelper
    ): LocalDataSource {
        return LocalDataSourceImpl(preferencesHelper)
    }

    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        @ApplicationContext context: Context,
        weatherApiService: OpenWeatherApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(context, weatherApiService)
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): IErrorHandler {
        return ErrorHandler()
    }

}