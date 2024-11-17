package com.example.marvelapp.presentation.components.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.marvelapp.presentation.components.states.LoadingState
import com.example.marvelapp.presentation.screens.CharactersViewModel

@Composable
fun CharactersList(charactersViewModel: CharactersViewModel, navController: NavHostController) {

    val isLoading by charactersViewModel.isLoading.collectAsState()
    val isError by charactersViewModel.errorResponse.collectAsState()
    val state by charactersViewModel.state.collectAsState()
    val charactersList by charactersViewModel.characterList.collectAsState()

    // State to manage scrolling
    val listState = rememberLazyListState()
    // Check if the user is at the end of the list
    val isAtEnd = remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex == (charactersList.size - 1)
        }
    }
// Trigger side effects
    LaunchedEffect(isAtEnd.value) {
        if (isAtEnd.value) {
            println("Reached the end of the list")
            // Perform actions like loading more data
            charactersViewModel.getCharactersList()
        }
    }
//    isLoading.takeIf { it }?.let {
//        LoadingState()
//    }
    LazyColumn(
        modifier = Modifier.padding(),
        state = listState
    ) {
        items(charactersList) { character ->
            character?.let {
                CharacterItem(character = it,
                    onCharacterClick = {
                        navController.navigate("characterDetails")
                    })
            }
        }
        isLoading.takeIf { it }?.let {
            item {
                LoadingState()
            }
        }
//        if (isLoading) {
//            item {
//                LoadingState()
//            }
//        }
    }
}