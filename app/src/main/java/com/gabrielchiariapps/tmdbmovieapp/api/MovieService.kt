package com.gabrielchiariapps.tmdbmovieapp.api

import com.gabrielchiariapps.tmdbmovieapp.model.MovieRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieRequest>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): Call<MovieRequest>
}
