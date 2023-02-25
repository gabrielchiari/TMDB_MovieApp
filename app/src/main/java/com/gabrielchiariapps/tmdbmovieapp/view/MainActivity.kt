package com.gabrielchiariapps.tmdbmovieapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gabrielchiariapps.tmdbmovieapp.model.Movie
import com.gabrielchiariapps.tmdbmovieapp.ui.MovieDetail
import com.gabrielchiariapps.tmdbmovieapp.ui.MovieList
import com.gabrielchiariapps.tmdbmovieapp.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TMDbMovieApp()
        }

    }
}

@Composable
fun TMDbMovieApp() {
    val viewModel: MoviesViewModel = viewModel()
    val movies: List<Movie> by viewModel.movies.observeAsState(emptyList())
    val selectedMovie: Movie? by viewModel.selectedMovie.observeAsState(null)
//    var searchText by remember { mutableStateOf("") }

    if (selectedMovie != null) {
        val movie = movies.find { it.id == selectedMovie !!.id }
        if (movie != null) {
            MovieDetail(movie = movie)
        }
    } else {
        MovieList(
            movies = movies,
            viewModel = viewModel
        )
    }
}
