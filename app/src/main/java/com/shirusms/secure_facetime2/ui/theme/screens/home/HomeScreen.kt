package com.shirusms.secure_facetime2.ui.theme.screens.home

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(android.Manifest.permission.READ_CONTACTS)
    var contacts by remember { mutableStateOf<List<Contact>>(emptyList()) }

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            contacts = fetchContacts(context)
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Start a New Call", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        if (contacts.isEmpty()) {
            Text("No contacts found or permission not granted.")
        } else {
            LazyColumn {
                items(contacts) { contact ->
                    ContactItem(contact = contact) {
                        Log.d("CallStart", "Calling ${contact.name} at ${contact.number}")
                        navController?.navigate("call_screen/${contact.number}")
                    }
                }
            }
        }
    }
}

data class Contact(val name: String, val number: String)

@Composable
fun ContactItem(contact: Contact, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
        Text(text = contact.number, style = MaterialTheme.typography.bodySmall)
    }
    Divider()
}

fun fetchContacts(context: Context): List<Contact> {
    val contactList = mutableListOf<Contact>()
    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null, null, null, null
    )
    cursor?.use {
        val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

        while (it.moveToNext()) {
            val name = it.getString(nameIndex)
            val number = it.getString(numberIndex)
            contactList.add(Contact(name, number))
        }
    }
    return contactList
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
