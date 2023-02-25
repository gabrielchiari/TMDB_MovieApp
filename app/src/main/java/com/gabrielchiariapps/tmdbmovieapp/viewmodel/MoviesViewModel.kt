package com.gabrielchiariapps.tmdbmovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielchiariapps.tmdbmovieapp.api.MovieAPI
import com.gabrielchiariapps.tmdbmovieapp.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import retrofit2.await

class MoviesViewModel : ViewModel() {

    private val movieAPI = MovieAPI()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _selectedMovie = MutableLiveData<Movie?>()
    val selectedMovie: LiveData<Movie?> = _selectedMovie

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            val response = movieAPI.getPopularMovies().await()
            _movies.value = response.results
        }
    }

    fun fetchSearchMovies(query: String) {
        viewModelScope.launch {
            val response = movieAPI.searchMovies(query).await()
            _movies.value = response.results
        }
    }

    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    fun clearSelectedMovie() {
        _selectedMovie.value = null
    }

    fun searchMovies(query: String) {
        if (query.isNotEmpty()) {
            fetchSearchMovies(query)
        } else {
            fetchMovies()
        }
    }
}