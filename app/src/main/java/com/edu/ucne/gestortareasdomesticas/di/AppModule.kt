package com.edu.ucne.gestortareasdomesticas.di

import com.edu.ucne.gestortareasdomesticas.data.remote.TareaApi
import com.edu.ucne.gestortareasdomesticas.data.repository.EmpleadoRepository
import com.edu.ucne.gestortareasdomesticas.data.repository.TareasRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideTareas(moshi: Moshi): TareaApi {
        return Retrofit.Builder()
            .baseUrl("https://gestordomestico.somee.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TareaApi::class.java)
    }

}