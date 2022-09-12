package com.example.demo_app.di

import com.example.demo_app.data.api.DemoApi
import com.example.demo_app.data.repository.DemoRepository
import com.example.demo_app.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDemoApi(): DemoApi {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .build()
            .create(DemoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDemoRepository(api: DemoApi): Repository {
        return DemoRepository(api)
    }

}