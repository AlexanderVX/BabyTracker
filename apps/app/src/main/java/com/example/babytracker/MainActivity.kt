package com.example.babytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.babytracker.ui.screens.LoginScreen
import com.example.babytracker.ui.screens.SplashScreen
import com.example.babytracker.ui.theme.BabyTrackerTheme

// главный экран приложения — точка входа
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BabyTrackerTheme {
                // создаём контроллер навигации — «пульт» переключения экранов
                val navController = rememberNavController()
                // NavHost — контейнер, внутри которого живут все экраны
                NavHost(
                    navController = navController,
                    startDestination = "splash"   // с какого экрана начинаем
                ) {
                    // маршрут с именем "splash" ведёт на функцию SplashScreen
                    composable("splash") { SplashScreen(navController) }
                    composable("login")  { LoginScreen(navController) }
                }
            }
        }
    }
}


