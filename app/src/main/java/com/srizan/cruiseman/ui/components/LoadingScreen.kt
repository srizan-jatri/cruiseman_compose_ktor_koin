package com.srizan.cruiseman.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    ColumnFullScreen(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    Surface {
        LoadingScreen()
    }
}