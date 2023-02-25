package com.gabrielchiariapps.tmdbmovieapp.api

import com.gabrielchiariapps.tmdbmovieapp.model.MovieRequest
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MovieAPI {

    private val service: MovieService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("api_key", "4987d79c0d54a109054f941aa61d75b1")
                    .build()
                val modifiedRequest = originalRequest.newBuilder()
                    .url(modifiedUrl)
                    .build()
                chain.proceed(modifiedRequest)
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        service = retrofit.create(MovieService::class.java)
    }

    suspend fun getPopularMovies(): Call<MovieRequest> {
        return withContext(Dispatchers.IO) {
            service.getPopularMovies()
        }
    }

    suspend fun searchMovies(query: String): Call<MovieRequest> {
        return withContext(Dispatchers.IO) {
            service.searchMovies(query)
        }

    }

}
