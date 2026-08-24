package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Light theme with black buttons, black text, and crisp outlines
            val blackTheme = lightColorScheme(
                primary = Color.Black,
                onPrimary = Color.White,          // Text inside solid buttons
                secondary = Color.Black,
                onSecondary = Color.White,
                background = Color.White,
                onBackground = Color.Black,        // Normal body text
                surface = Color(0xFFF5F5F5),       // Light gray cards
                onSurface = Color.Black,           // Text on cards
                outline = Color.Black              // OutlinedTextField borders
            )

            MaterialTheme(colorScheme = blackTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp()
                }
            }
        }
    }
}