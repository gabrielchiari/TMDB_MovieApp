package com.gabrielchiariapps.tmdbmovieapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gabrielchiariapps.tmdbmovieapp.R

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    val searchText = remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText.value,
        onValueChange = {
            searchText.value = it
            onSearch(it)
        },
        label = {
            Text(text = "Search movies...")
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
