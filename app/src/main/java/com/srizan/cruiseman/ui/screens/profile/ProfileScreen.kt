package com.srizan.cruiseman.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    name: String,
    phone: String,
    address: String,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Name: $name", style = MaterialTheme.typography.titleLarge)
                Text("Phone: $phone")
                Text("Address: $address")
                Spacer(Modifier.size(16.dp))
                OutlinedButton(
                    onClick = onLogoutClick,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Logout")
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    Surface {
        ProfileScreen(
            name = "Srizan",
            phone = "019xxxxxxx",
            address = "Dhaka, Bangladesh",
            {}
        )
    }
}