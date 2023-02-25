package com.gabrielchiariapps.tmdbmovieapp.api

import com.gabrielchiariapps.tmdbmovieapp.model.MovieResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MovieAPI {

    private val service: MovieService

    init {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", "4987d79c0d54a109054f941aa61d75b1")
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)

        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        service = retrofit.create(MovieService::class.java)
    }

    fun getPopularMovies(): List<MovieResponse> {
        return service.getPopularMovies()
    }

    fun searchMovies(query: String): List<MovieResponse> {
        return service.searchMovies(query)
    }

}
