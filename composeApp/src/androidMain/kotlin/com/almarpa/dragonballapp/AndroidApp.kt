package com.almarpa.dragonballapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.almarpa.dragonballapp.core.navigation.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}