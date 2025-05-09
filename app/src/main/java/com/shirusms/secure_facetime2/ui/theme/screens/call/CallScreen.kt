package com.shirusms.secure_facetime2.ui.theme.screens.call

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Videocam

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CallScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Title
            Text(
                text = "Secure FaceTime Call",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp)
            )

            // Bottom Control Buttons
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // MIC ICON
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = "Mic",
                    tint = Color.Black,
                    modifier = Modifier.size(56.dp)
                )

                // CAMERA ICON
                Icon(
                    imageVector = Icons.Filled.Videocam,
                    contentDescription = "Camera",
                    tint = Color.Black,
                    modifier = Modifier.size(56.dp)
                )

                // END CALL ICON
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "End Call",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(56.dp)
                        .clickable {
                            navController.navigate("home") {
                                popUpTo("call") { inclusive = true }
                            }
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CallScreenPreview() {
    val navController = rememberNavController()
    CallScreen(navController)
}
