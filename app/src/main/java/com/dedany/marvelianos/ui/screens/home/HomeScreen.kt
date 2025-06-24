package com.dedany.marvelianos.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dedany.domain.entities.Character


@Composable
fun HomeScreen(
    viewModel: CharacterViewModel = viewModel()
) {

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }


    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (it.text.isNotEmpty()) {
                    viewModel.searchCharacters(it.text)
                } else {
                    viewModel.loadCharacters()
                }
            },
            label = { Text("Buscar personaje") },
            modifier = Modifier.fillMaxSize()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            if (characters.isEmpty()) {
                Text(
                    "No se encontraron personajes",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(characters) { character ->
                        CharacterItem(character)
                        HorizontalDivider()
                    }

                }
            }
        }
    }
}
@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Imagen del personaje
            AsyncImage(
                model = character.thumbnail,
                contentDescription = character.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )

            Column {
                // Nombre del personaje
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium
                )

                // Descripción, si existe
                if (character.description.isNotBlank()) {
                    Text(
                        text = character.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
