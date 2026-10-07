package com.t2.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

import com.t2.ui.theme.Azul
import com.t2.ui.theme.Blanco
import com.t2.ui.theme.Celeste
import com.t2.ui.theme.Gris
import com.t2.data.preferences.SessionManager

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    var telefono by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var errorTelefono by remember { mutableStateOf(false) }
    var errorOtp by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar Sesión",
            color = Celeste,
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = {
                if (it.length <= 9 && it.all { char -> char.isDigit() }) {
                    telefono = it
                }
            },
            label = {
                Text(text = "Teléfono", style = MaterialTheme.typography.bodyMedium)
            },
            leadingIcon = {
                Text(
                    text = "+51",
                    modifier = Modifier.padding(start = 8.dp),
                    color = Gris,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorTelefono,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Blanco,
                unfocusedTextColor = Blanco,
                focusedBorderColor = Celeste,
                unfocusedBorderColor = Gris,
                focusedLabelColor = Celeste,
                unfocusedLabelColor = Gris
            ),
            supportingText = {
                if (errorTelefono) {
                    Text(
                        text = "Debe tener exactamente 9 dígitos",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        )

        OutlinedTextField(
            value = otp,
            onValueChange = {
                if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                    otp = it
                }
            },
            label = {
                Text(text = "Código OTP", style = MaterialTheme.typography.bodyMedium)
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            isError = errorOtp,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Blanco,
                unfocusedTextColor = Blanco,
                focusedBorderColor = Celeste,
                unfocusedBorderColor = Gris,
                focusedLabelColor = Celeste,
                unfocusedLabelColor = Gris
            ),
            supportingText = {
                if (errorOtp) {
                    Text(
                        text = "El código OTP debe ser exactamente de 6 dígitos",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        )

        Button(
            onClick = {
                errorTelefono = telefono.length != 9
                errorOtp = otp.length != 6

                if (!errorTelefono && !errorOtp) {
                    val telefonoRegistrado = sessionManager.getCelular()
                    val otpRegistrado = sessionManager.getOtpGuardado()

                    if (telefono == telefonoRegistrado && otp == otpRegistrado) {
                        onLoginSuccess()
                    } else {
                        Toast.makeText(
                            context,
                            "Credenciales incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Azul),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Ingresar",
                color = Blanco,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        TextButton(onClick = onNavigateToRegister) {
            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = Celeste,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}