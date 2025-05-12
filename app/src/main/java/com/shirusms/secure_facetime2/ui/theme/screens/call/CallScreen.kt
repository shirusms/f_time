package com.shirusms.secure_facetime2.ui.theme.screens.call


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shirusms.secure_facetime2.ui.theme.Secure_FaceTime2Theme


@Composable
fun CallScreen(navController: NavController) {
    var micOn by remember { mutableStateOf(true) }
    var videoOn by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { micOn = !micOn }) {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = "Mic",
                    tint = if (micOn) Color.White else Color.Gray
                )
            }

            IconButton(onClick = {
                navController.popBackStack() // Simulate call end
            }) {
                Icon(
                    imageVector = Icons.Default.CallEnd,
                    contentDescription = "End Call",
                    tint = Color.Red
                )
            }

            IconButton(onClick = { videoOn = !videoOn }) {
                Icon(
                    imageVector = Icons.Default.Videocam,
                    contentDescription = "Video",
                    tint = if (videoOn) Color.White else Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CallScreenPreview() {
    Secure_FaceTime2Theme {
        CallScreen(navController = rememberNavController())
    }
}
