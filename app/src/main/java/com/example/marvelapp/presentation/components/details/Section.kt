package com.example.marvelapp.presentation.components.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Section(
    title: String,
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    titleContent: @Composable () -> Unit = {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    },
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Box(modifier = titleModifier.padding(horizontal = 16.dp)) {
            titleContent()
        }
        Box(modifier = contentModifier) {
            content()
        }
    }
}