package com.shirusms.secure_facetime2.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shirusms.secure_facetime2.R
import com.shirusms.secure_facetime2.navigation.ROUTE_CALL


@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Welcome Text
            Text(
                text = "Welcome to Secure FaceTime!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        // Navigate to CallScreen
                        navController.navigate(ROUTE_CALL)
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(text = "Start New Call", fontSize = 18.sp)
                }

                Button(
                    onClick = {
                        // In future navigate to ProfileScreen
                        // navController.navigate(Routes.ProfileScreen.route)
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(text = "Profile / Settings", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Optional: Logo or App Icon at bottom
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp)
            )
        }
    }
}
@Preview
@Composable
fun Homepreview() {
    HomeScreen(rememberNavController())
}
