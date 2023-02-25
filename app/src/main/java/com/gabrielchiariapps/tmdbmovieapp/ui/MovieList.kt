package com.gabrielchiariapps.tmdbmovieapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.gabrielchiariapps.tmdbmovieapp.R
import com.gabrielchiariapps.tmdbmovieapp.model.Movie

@Composable
fun MovieList(
    movies: List<Movie>,
    onMovieSelected: (Movie) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieListItem(movie = movie, onMovieSelected = onMovieSelected)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieListItem(movie: Movie, onMovieSelected: (Movie) -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = { onMovieSelected(movie) })
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w185/${movie.posterPath}",
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.placeholder_image)
                        error(R.drawable.error_image)
                    }
                )
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(4.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}
