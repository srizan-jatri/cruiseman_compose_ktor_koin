package com.srizan.cruiseman.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    ColumnFullScreen(modifier = modifier) {
        Text(message)
        OutlinedButton(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    Surface {
        ErrorScreen(
            "Error",
            onRetry = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}