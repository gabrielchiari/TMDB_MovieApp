package com.gabrielchiariapps.tmdbmovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielchiariapps.tmdbmovieapp.api.MovieAPI
import com.gabrielchiariapps.tmdbmovieapp.model.MovieResponse
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val movieAPI = MovieAPI()
    private val _movies = MutableLiveData<List<MovieResponse>>()
    val movies: LiveData<List<MovieResponse>> = _movies

    private val _selectedMovie = MutableLiveData<Int?>()
    val selectedMovie: LiveData<Int?> = _selectedMovie

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            val response = movieAPI.getPopularMovies()
            _movies.value = response
        }
    }

    fun setSelectedMovie(movieId: Int) {
        _selectedMovie.value = movieId
    }

    fun clearSelectedMovie() {
        _selectedMovie.value = null
    }

}