package com.example.marvelapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.presentation.components.details.CharacterDetails
import com.example.marvelapp.presentation.components.list.CharactersList

@Composable
fun CharactersApp(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    Scaffold(
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "CharacterList",
            modifier = Modifier.padding(padding)
        ) {
            composable("CharacterList") {
                CharactersList(
                    charactersViewModel = charactersViewModel,
                    navController = navController
                )
            }
            composable(
                route = "characterDetails/",
            ) {
                CharacterDetails(
                    navController
                )
            }
        }
    }
}