package com.example.babytracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    // LaunchedEffect — «выполни код один раз при появлении экрана»
    LaunchedEffect(Unit) {
        // подождём 1.5 секунды — имитация проверки сессии
        delay(1500)
        // переходим на логин; popUpTo(0) — стереть историю, чтобы «Назад» не возвращал на splash
        navController.navigate("login") { popUpTo(0) }
    }
    Box(   // Box — контейнер, дети накладываются друг на друга
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🍼", style = MaterialTheme.typography.displayLarge)
            Spacer(Modifier.height(16.dp))
            Text("МалышТрекер", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))
            CircularProgressIndicator()   // крутящийся индикатор загрузки
        }
    }
}