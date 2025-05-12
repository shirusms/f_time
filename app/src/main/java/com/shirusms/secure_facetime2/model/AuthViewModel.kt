package com.shirusms.secure_facetime2.model


import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onError("Email and password cannot be empty.")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Login failed.")
                }
            }
    }

    fun logout(onComplete: () -> Unit) {
        auth.signOut()
        onComplete()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentUserEmail(): String? {
        return auth.currentUser?.email
    }
    fun registerUser(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(task.exception?.message ?: "Unknown error")
                }
            }
    }

}
