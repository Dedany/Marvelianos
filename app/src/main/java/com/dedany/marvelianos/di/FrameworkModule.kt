package com.dedany.marvelianos.di

import com.dedany.marvelianos.data.datasource.remote.api.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.MediaType.Companion.toMediaType
import java.math.BigInteger
import java.security.MessageDigest


@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://gateway.marvel.com/v1/public/"

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(
    @Named("apikey") apiKey: String,
    @Named("publickey") publicKey: String,
    @Named("privatekey") privateKey: String
    ): Interceptor {
        return Interceptor { chain ->
            val timestamp = System.currentTimeMillis().toString()
            val hash = (timestamp + privateKey + publicKey).md5()

            val original = chain.request()
            val newUrl = original.url.newBuilder()
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("ts", timestamp)
                .addQueryParameter("hash", hash)
                .build()

            val newRequest = original.newBuilder().url(newUrl).build()
            chain.proceed(newRequest)

        }
    }



    @Provides
    @Singleton
    fun providesOkHttpClient(apiKeyInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        // puedes añadir más configuración si quieres
    }


    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient,baseUrl: String,json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

    }

    @Provides
    @Singleton
    fun provideMarvelService(retrofit: Retrofit): MarvelService {
        return retrofit.create(MarvelService::class.java)
    }
}
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')
}

