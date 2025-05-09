package com.shirusms.secure_facetime2.ui.theme.screens.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun SplashScreen(navController: NavController) {
    // Delay navigation to login after 2 seconds
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }, 2000)
    }

    // UI for Splash Screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Secure Facetime", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}
