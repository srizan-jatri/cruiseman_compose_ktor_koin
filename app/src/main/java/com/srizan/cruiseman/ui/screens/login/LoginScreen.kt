package com.srizan.cruiseman.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var phone by remember { mutableStateOf("01918261847") }
    var password by remember { mutableStateOf("01918261847") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Jatri CruiseMan", style = MaterialTheme.typography.headlineLarge)
        Text("( Ktor - Koin sample project )", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.size(48.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Phone") }
        )
        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") }
        )
        Spacer(Modifier.size(32.dp))
        Button(
            onClick = { onLoginClick(phone, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}