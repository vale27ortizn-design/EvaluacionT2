package com.t2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.t2.R
import com.t2.data.preferences.SessionManager
import com.t2.ui.theme.Azul
import com.t2.ui.theme.Blanco
import com.t2.ui.theme.Gris

@Composable
fun PerfilScreen(
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val preferences = context.getSharedPreferences("dami_t2", android.content.Context.MODE_PRIVATE)

    // Recuperamos TODOS los datos guardados en el formulario de registro
    val nombres = preferences.getString("nombres", "Usuario") ?: ""
    val apPaterno = preferences.getString("apPaterno", "") ?: ""
    val apMaterno = preferences.getString("apMaterno", "") ?: ""
    val dni = preferences.getString("dni", "Sin DNI") ?: ""
    val correo = preferences.getString("correo", "Sin correo") ?: ""
    val celular = preferences.getString("celular", "Sin celular") ?: ""
    val genero = preferences.getString("genero", "No especificado") ?: ""
    val estadoCivil = preferences.getString("estadoCivil", "No especificado") ?: ""
    val peso = preferences.getString("peso", "-") ?: ""
    val altura = preferences.getString("altura", "-") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_perfil),
            contentDescription = "Icono Perfil",
            modifier = Modifier.size(100.dp),
            tint = Azul
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Mi Perfil", color = Azul, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Blanco),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Nombres: $nombres",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Gris, thickness = 0.5.dp)

                Text(
                    text = "Apellidos: $apPaterno $apMaterno",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Gris, thickness = 0.5.dp)

                Text(
                    text = "DNI: $dni",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Gris, thickness = 0.5.dp)

                Text(
                    text = "Correo: $correo",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Gris, thickness = 0.5.dp)

                Text(
                    text = "Celular: $celular",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Gris, thickness = 0.5.dp)

                Text(
                    text = "Género: $genero",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Gris, thickness = 0.5.dp)

                Text(
                    text = "Estado Civil: $estadoCivil",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Gris, thickness = 0.5.dp)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Peso: $peso kg",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Altura: $altura cm",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                sessionManager.clearSession()
                onLogout()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Azul),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Cerrar Sesión", color = Blanco, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}