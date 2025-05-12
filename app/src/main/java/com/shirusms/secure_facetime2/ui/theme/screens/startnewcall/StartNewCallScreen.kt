package com.shirusms.secure_facetime2.ui.theme.screens.startnewcall

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StartNewCallScreen(navController: NavController) {
    val contactPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)

    LaunchedEffect(Unit) {
        if (!contactPermissionState.status.isGranted) {
            contactPermissionState.launchPermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Start New Call") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (contactPermissionState.status.isGranted) {
                    Text("Tap below to choose a contact to call")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        navController.navigate("ROUTE_CONTACT_LIST")
                    }) {
                        Text("Choose Contact")
                    }
                } else {
                    Text("Contact permission is required to proceed.")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StartNewCallScreenPreview() {
    StartNewCallScreen(navController = rememberNavController())
}
