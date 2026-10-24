package com.example.babytracker.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// описываем светлое оформление: какие цвета за что отвечают
private val LightColors = lightColorScheme(
    primary = MintLight,          // кнопки и активные элементы
    secondary = PeachLight,       // вторичный акцент, фон карточек
    tertiary = LavenderLight,     // третий акцент, метки
    background = BgLight,         // фон экрана
    onPrimary = TextDark,         // текст НА мятной кнопке — тёмный
    onBackground = TextDark,      // обычный текст на фоне
)

// тёмное оформление — по тем же правилам, но из тёмной палитры
private val DarkColors = darkColorScheme(
    primary = MintDark,
    secondary = PeachDark,
    tertiary = LavenderDark,
    background = BgDark,
    onPrimary = TextDark,         // на приглушённом мятном текст снова тёмный
    onBackground = TextLight,
)

// главная функция темы: Compose вызывает её сам при старте приложения
@Composable
fun BabyTrackerTheme(
    // спрашиваем систему: телефон сейчас в тёмной теме?
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit   // экраны, которые надо раскрасить
) {
    // выбираем палитру по настройке телефона
    val colors = if (darkTheme) DarkColors else LightColors
    // применяем палитру ко всему, что внутри
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}