package com.shirusms.secure_facetime2.ui.theme.screens.contactlist


import android.content.Context
import android.provider.ContactsContract
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.shirusms.secure_facetime2.navigation.ROUTE_CALL

// ✅ Data class
data class Contact(val name: String, val phoneNumber: String)

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavController) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(android.Manifest.permission.READ_CONTACTS)
    var contacts by remember { mutableStateOf<List<Contact>>(emptyList()) }

    LaunchedEffect(permissionState.status.isGranted) {
        if (permissionState.status.isGranted) {
            contacts = loadContacts(context)
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Select Contact") })
        },
        content = { padding ->
            if (!permissionState.status.isGranted) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Permission not granted")
                }
            } else if (contacts.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No contacts found.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    items(contacts) { contact ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(ROUTE_CALL)
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = contact.phoneNumber, style = MaterialTheme.typography.bodyMedium)
                            Divider(modifier = Modifier.padding(top = 8.dp))
                        }
                    }
                }
            }
        }
    )
}

// ✅ Function to load contacts
fun loadContacts(context: Context): List<Contact> {
    val contactsList = mutableListOf<Contact>()
    val contentResolver = context.contentResolver
    val cursor = contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null, null, null, null
    )
    cursor?.use {
        val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        while (it.moveToNext()) {
            val name = it.getString(nameIndex) ?: "Unknown"
            val number = it.getString(numberIndex) ?: "N/A"
            contactsList.add(Contact(name, number))
        }
    }
    return contactsList
}

// ✅ Preview with dummy data
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ContactListScreenPreview() {
    val dummyContacts = listOf(
        Contact("Alice", "+123456789"),
        Contact("Bob", "+987654321"),
        Contact("Charlie", "+112233445")
    )
    Scaffold(
        topBar = { TopAppBar(title = { Text("Preview Contacts") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(dummyContacts) { contact ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* No action in preview */ }
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = contact.phoneNumber, style = MaterialTheme.typography.bodyMedium)
                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
