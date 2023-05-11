package com.example.readerapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.firebase.auth.FirebaseAuth
import android.content.SharedPreferences

fun saveLoggedUser(context: Context) {
    // Create a FirebaseAuth instance.
    val auth = FirebaseAuth.getInstance()

    // Get the current user.
    val user = auth.currentUser

    // Save the user's email and uid to SharedPreferences.
    val sharedPreferences = context.getSharedPreferences("my_app_preferences", MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("email", user?.email)
        putString("uid", user?.uid)
        apply()
    }
}

fun clearLoggedUser(context: Context) {
    // Get the SharedPreferences instance.
    val sharedPreferences = context.getSharedPreferences("my_app_preferences", MODE_PRIVATE)

    // Clear the SharedPreferences.
    with(sharedPreferences.edit()) {
        clear()
        apply()
    }
}