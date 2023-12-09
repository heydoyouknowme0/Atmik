package com.build.atmik

import android.content.Context
import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Example: MainActivity.kt
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.RGBA_8888)
        setContentView(R.layout.activity_main)
        // Check if the user profile exists
        if (!doesUserProfileExist()) {
            // Display UI for collecting user information
            showUserProfileDialog()
        } else {
            // Continue with the normal flow of your app
            setContentView(R.layout.imp_dont)
            // ...
        }
    }

    private fun doesUserProfileExist(): Boolean {
        // Implement logic to check if the user profile exists
        // You can use SharedPreferences or other methods for this
        // Return true if the user profile exists, false otherwise

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "")!!
        val userEmail = sharedPreferences.getString("user_email", "")!!


        return userName.isNotEmpty() && userEmail.isNotEmpty()


    }

    private fun showUserProfileDialog() {
        // Implement logic to display the UI for collecting user information
        // Set an OnClickListener for the "Save Profile" button to save the user information
        // Save the user information using SharedPreferences or other storage methods
        // Once the information is saved, continue with the normal flow of your app
    }
}

