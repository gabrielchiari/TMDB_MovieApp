package com.gabrielchiariapps.tmdbmovieapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.gabrielchiariapps.tmdbmovieapp.EndPoints
import com.gabrielchiariapps.tmdbmovieapp.model.Movie
import com.gabrielchiariapps.tmdbmovieapp.viewmodel.MoviesViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieDetail(movie: Movie) {
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
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop,
                contentDescription = movie.title,
                painter = rememberImagePainter(
                    data = EndPoints.IMAGE_URL_BACKDROP + movie.backdropPath,
                    builder = {
                        crossfade(true)
                        placeholder(com.gabrielchiariapps.tmdbmovieapp.R.drawable.placeholder_image)
                        error(com.gabrielchiariapps.tmdbmovieapp.R.drawable.error_image)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            movie.overview?.let { it ->
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
            }
        }
    }
}
