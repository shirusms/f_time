package com.shirusms.secure_facetime2.ui.theme.screens.login


import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shirusms.secure_facetime2.model.AuthViewModel
import com.shirusms.secure_facetime2.ui.theme.Secure_FaceTime2Theme


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                authViewModel.loginWithEmail(
                    email = email,
                    password = password,
                    onSuccess = onLoginSuccess,
                    onError = { errorMessage = it }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Secure_FaceTime2Theme {
        // Static preview without auth logic
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Secure_FaceTime")
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") })
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })
            Button(onClick = {}) { Text("Login") }
        }
    }
}
