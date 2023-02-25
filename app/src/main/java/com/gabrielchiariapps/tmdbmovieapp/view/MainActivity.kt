package com.gabrielchiariapps.tmdbmovieapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.gabrielchiariapps.tmdbmovieapp.model.Movie
import com.gabrielchiariapps.tmdbmovieapp.ui.MovieDetail
import com.gabrielchiariapps.tmdbmovieapp.ui.MovieList
import com.gabrielchiariapps.tmdbmovieapp.viewmodel.MoviesViewModel


class MainActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val movies: List<Movie> by viewModel.movies.observeAsState(emptyList())
            val selectedMovie: Int? by viewModel.selectedMovie.observeAsState(null)

            if (selectedMovie != null) {
                val movie = movies.find { it.id == selectedMovie }
                if (movie != null) {
                    MovieDetail(movie = movie)
                }
            } else {
                MovieList(movies = movies, onMovieSelected = { movie ->
                    viewModel.setSelectedMovie(movie.id)
                })
            }
        }
    }
}