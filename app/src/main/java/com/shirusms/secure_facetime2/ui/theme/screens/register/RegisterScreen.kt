package com.shirusms.secure_facetime2.ui.theme.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shirusms.secure_facetime2.ui.theme.Secure_FaceTime2Theme


@Composable
fun RegisterScreen(
    navController: NavController,
    onRegisterClick: (String, String) -> Unit = { _, _ -> },
    onBackToLoginClick: () -> Unit = {},

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Register", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { onRegisterClick(email, password) }) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onBackToLoginClick) {
                Text("Already have an account? Login")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    Secure_FaceTime2Theme  {
        RegisterScreen(rememberNavController())
    }
}