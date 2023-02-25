package com.gabrielchiariapps.tmdbmovieapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gabrielchiariapps.tmdbmovieapp.model.MovieResponse
import com.gabrielchiariapps.tmdbmovieapp.viewmodel.MoviesViewModel
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun MovieDetail(movie: MovieResponse) {
    val viewModel: MoviesViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie.title,
                        color = Color.White
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModel.clearSelectedMovie()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { it ->
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                data = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            movie.overview?.let { it1 ->
                Text(
                    text = it1,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
            }
        }
    }
}
