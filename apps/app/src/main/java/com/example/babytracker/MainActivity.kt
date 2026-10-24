package com.example.babytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.babytracker.ui.screens.HelloScreen
import com.example.babytracker.ui.theme.BabyTrackerTheme

// главный экран приложения — точка входа
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {                        // говорим: интерфейс рисуем через Compose
            BabyTrackerTheme {              // наша тема
                HelloScreen()               // показываем главный экран
            }
        }
    }
}


