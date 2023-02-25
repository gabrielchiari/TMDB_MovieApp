package com.gabrielchiariapps.tmdbmovieapp.api

import com.gabrielchiariapps.tmdbmovieapp.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(): List<MovieResponse>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): List<MovieResponse>
}
