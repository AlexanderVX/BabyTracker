package com.example.babytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.babytracker.ui.theme.BabyTrackerTheme

// главный экран приложения — точка входа
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {                        // говорим: интерфейс рисуем через Compose
            BabyTrackerTheme {              // наша тема из части 1
                HelloScreen()               // показываем главный экран
            }
        }
    }
}

// composable-функция — «чертёж» части экрана
@Composable
fun HelloScreen() {
    // remember: хранит значение между перерисовками экрана
    var count by remember { mutableIntStateOf(0) }

    // Column = вертикальный контейнер (как LinearLayout в старом Android)
    Column(
        // Modifier — «настройщик» элемента: размер, отступы, выравнивание
        modifier = Modifier
            .fillMaxSize()                  // занять весь экран
            .padding(24.dp),                // отступ 24dp со всех сторон
        horizontalAlignment = Alignment.CenterHorizontally, // по центру горизонтали
        verticalArrangement = Arrangement.Center            // по центру вертикали
    ) {
        Text("Привет, МалышТрекер!")        // просто текст
        Text("Нажали кнопок: $count")       // $count подставит значение переменной
        Spacer(Modifier.height(16.dp))      // пустое место 16dp
        Button(onClick = { count++ }) {     // при нажатии увеличиваем счётчик
            Text("Нажми меня")
        }
    }
}

