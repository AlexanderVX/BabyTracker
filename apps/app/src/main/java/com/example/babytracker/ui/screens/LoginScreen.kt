package com.example.babytracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {
    // три состояния экрана. remember запоминает между перерисовками,
    // mutableStateOf делает значение «наблюдаемым» — при изменении экран перерисуется
    var email by remember { mutableStateOf("") }       // текст в поле почты
    var password by remember { mutableStateOf("") }    // текст пароля
    var error by remember { mutableStateOf<String?>(null) } // String? — ошибки может не быть

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Вход", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        // поле ввода: value — что показывать, onValueChange — что делать при вводе
        OutlinedTextField(
            value = email,                                   // показываем текущее состояние
            onValueChange = { email = it },                  // пишем новое значение в состояние
            label = { Text("Почта") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            // пароль скрыт точками
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        // если ошибка есть — показываем её красным; если null — блок не рисуем вообще
        error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                // простая валидация «руками»: проверяем поля до отправки на сервер
                if (!email.contains("@")) {
                    error = "Похоже, это не почта"           // кладём сообщение в состояние
                } else if (password.length < 8) {
                    error = "Пароль не короче 8 символов"
                } else {
                    error = null                             // всё чисто — сбрасываем ошибку
                    // пока сервера нет: просто переходим дальше
                    navController.navigate("dashboard_mock") { popUpTo(0) }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Продолжить")
        }
    }
}
