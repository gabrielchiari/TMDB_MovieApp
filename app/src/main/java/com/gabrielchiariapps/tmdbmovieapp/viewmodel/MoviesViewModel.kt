package com.gabrielchiariapps.tmdbmovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielchiariapps.tmdbmovieapp.api.MovieAPI
import com.gabrielchiariapps.tmdbmovieapp.model.Movie
import kotlinx.coroutines.launch
import retrofit2.await

class MoviesViewModel : ViewModel() {

    private val movieAPI = MovieAPI()
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _selectedMovie = MutableLiveData<Int?>()
    val selectedMovie: LiveData<Int?> = _selectedMovie

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            val response = movieAPI.getPopularMovies().await()
            _movies.value = response.results
        }
    }

    private fun fetchSearchMovies(query: String) {
        viewModelScope.launch {
            val response = movieAPI.searchMovies(query).await()
            _movies.value = response.results
        }
    }

    fun setSelectedMovie(movieId: Int) {
        _selectedMovie.value = movieId
    }

    fun clearSelectedMovie() {
        _selectedMovie.value = null
    }

}