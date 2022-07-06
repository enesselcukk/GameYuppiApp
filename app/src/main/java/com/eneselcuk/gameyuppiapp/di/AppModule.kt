package com.eneselcuk.gameyuppiapp.di


import com.eneselcuk.gameyuppiapp.BuildConfig.BASE_URL
import com.eneselcuk.gameyuppiapp.data.remote.service.GameService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    internal fun providesFoodApi(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .baseUrl(BASE_URL)
            .build()

    }

    @Singleton
    @Provides
    fun provideGetQuotes(retrofit: Retrofit): GameService {
        return retrofit.create(GameService::class.java)
    }
}