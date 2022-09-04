package com.montymobile.callsignature.networking
import com.montymobile.interfaces.ApiInterface
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws

public const val BASE_URL_WEATHER: String = "http://api.openweathermap.org/data/2.5/"

fun buildClient(): OkHttpClient {
    val dispatcher = Dispatcher()
    dispatcher.maxRequests = 1

    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .dispatcher(dispatcher)

    return okHttpClientBuilder.build()
}

fun buildRetrofit(baseUrl: String): Retrofit {

    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun buildApiServiceForWeatherUpdates(): ApiInterface = buildRetrofit(BASE_URL_WEATHER).create(ApiInterface::class.java)