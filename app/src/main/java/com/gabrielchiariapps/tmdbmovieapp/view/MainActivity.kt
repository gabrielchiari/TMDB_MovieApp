package com.gabrielchiariapps.tmdbmovieapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TMDbMovieApp")
                },
                actions = {
                    IconButton(onClick = { viewModel.clearSelectedMovie() }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                    }
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            TextField(
                value = searchText,
                onValueChange = { newText -> searchText = newText },
                label = { Text("Search") },
                modifier = Modifier.padding(16.dp),
                singleLine = true,
            )
            if (selectedMovie != null) {
                val movie = movies.find { it.id == selectedMovie !!.id }
                if (movie != null) {
                    MovieDetail(movie = movie)
                }
            } else {
                MovieList(
                    movies = if (searchText.isNotEmpty()) {
                        viewModel.searchMovies(searchText)
                        movies
                    } else {
                        movies
                    },
                    onMovieSelected = { movie ->
                        viewModel.setSelectedMovie(movie)
                    }
                )
            }
        }
    }
}
