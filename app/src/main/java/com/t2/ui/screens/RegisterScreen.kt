package com.t2.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.t2.R
import com.t2.data.preferences.SessionManager

// Importaciones de tus colores
import com.t2.ui.theme.Azul
import com.t2.ui.theme.Blanco
import com.t2.ui.theme.Celeste
import com.t2.ui.theme.Gris

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    // Variables de estado
    var nombres by remember { mutableStateOf("") }
    var apPaterno by remember { mutableStateOf("") }
    var apMaterno by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var otpVisible by remember { mutableStateOf(false) }

    // Dialogs
    var genero by remember { mutableStateOf("") }
    var mostrarDialogoGenero by remember { mutableStateOf(false) }
    var estadoCivil by remember { mutableStateOf("") }
    var mostrarDialogoEstadoCivil by remember { mutableStateOf(false) }

    // Variables para validación de errores
    var errorNombres by remember { mutableStateOf(false) }
    var errorApPaterno by remember { mutableStateOf(false) }
    var errorApMaterno by remember { mutableStateOf(false) }
    var errorDni by remember { mutableStateOf(false) }
    var errorCelular by remember { mutableStateOf(false) }
    var errorCorreo by remember { mutableStateOf(false) }
    var errorPeso by remember { mutableStateOf(false) }
    var errorAltura by remember { mutableStateOf(false) }
    var errorOtp by remember { mutableStateOf(false) }
    var errorGenero by remember { mutableStateOf(false) }
    var errorEstadoCivil by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Fondo oscuro
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de Usuario",
            color = Celeste, // Letra clara
            // Nota: Para que este título use Quicksand, asegúrate de definir 'headlineMedium' en tu Type.kt
            style = MaterialTheme.typography.headlineMedium
        )

        // Nombres
        OutlinedTextField(
            value = nombres,
            onValueChange = {
                nombres = it
                if (nombres.isNotBlank()) errorNombres = false
            },
            label = { Text(text = "Nombres", style = MaterialTheme.typography.bodyMedium) }, // Aplicando Quicksand 14.sp[cite: 1]
            isError = errorNombres,
            singleLine = true,
            supportingText = { if (errorNombres) Text(text = "Los nombres son obligatorios", style = MaterialTheme.typography.labelMedium) else null }, // Aplicando Quicksand 12.sp[cite: 1]
            modifier = Modifier.fillMaxWidth()
        )

        // Apellido Paterno
        OutlinedTextField(
            value = apPaterno,
            onValueChange = {
                apPaterno = it
                if (apPaterno.isNotBlank()) errorApPaterno = false
            },
            label = { Text(text = "Apellido Paterno", style = MaterialTheme.typography.bodyMedium) },
            isError = errorApPaterno,
            singleLine = true,
            supportingText = { if (errorApPaterno) Text(text = "El apellido es obligatorio", style = MaterialTheme.typography.labelMedium) else null },
            modifier = Modifier.fillMaxWidth()
        )

        // Apellido Materno
        OutlinedTextField(
            value = apMaterno,
            onValueChange = {
                apMaterno = it
                if (apMaterno.isNotBlank()) errorApMaterno = false
            },
            label = { Text(text = "Apellido Materno", style = MaterialTheme.typography.bodyMedium) },
            isError = errorApMaterno,
            singleLine = true,
            supportingText = { if (errorApMaterno) Text(text = "El apellido es obligatorio", style = MaterialTheme.typography.labelMedium) else null },
            modifier = Modifier.fillMaxWidth()
        )

        // DNI
        OutlinedTextField(
            value = dni,
            onValueChange = {
                if (it.length <= 8 && it.all { char -> char.isDigit() }) {
                    dni = it
                    if (dni.length == 8) errorDni = false
                }
            },
            label = { Text(text = "DNI", style = MaterialTheme.typography.bodyMedium) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorDni,
            singleLine = true,
            supportingText = {
                if (errorDni) {
                    if (dni.isEmpty()) Text(text = "El DNI es obligatorio", style = MaterialTheme.typography.labelMedium)
                    else if (dni.length < 8) Text(text = "El DNI debe tener 8 dígitos", style = MaterialTheme.typography.labelMedium)
                } else null
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Genero
        Box(modifier = Modifier.fillMaxWidth().clickable { mostrarDialogoGenero = true }) {
            OutlinedTextField(
                value = genero,
                onValueChange = {},
                label = { Text(text = "Género", style = MaterialTheme.typography.bodyMedium) },
                readOnly = true,
                enabled = false,
                isError = errorGenero,
                singleLine = true,
                supportingText = { if (errorGenero) Text(text = "Selecciona tu género", style = MaterialTheme.typography.labelMedium) else null },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Blanco,
                    disabledBorderColor = if (errorGenero) MaterialTheme.colorScheme.error else Gris,
                    disabledLabelColor = if (errorGenero) MaterialTheme.colorScheme.error else Gris
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (mostrarDialogoGenero) {
            AlertDialog(
                onDismissRequest = { mostrarDialogoGenero = false },
                containerColor = Color(0xFF1E1E1E), // Fondo oscuro para el diálogo
                title = { Text(text = "Selecciona tu género", color = Blanco, style = MaterialTheme.typography.bodyLarge) }, // Aplicando Quicksand 16.sp[cite: 1]
                text = {
                    Column {
                        listOf("Femenino", "Masculino", "Prefiero no decirlo").forEach { opcion ->
                            TextButton(onClick = {
                                genero = opcion
                                errorGenero = false
                                mostrarDialogoGenero = false
                            }) {
                                Text(text = opcion, color = Celeste, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                },
                confirmButton = {}
            )
        }

        // Celular
        OutlinedTextField(
            value = celular,
            onValueChange = {
                if (it.length <= 9 && it.all { char -> char.isDigit() }) {
                    celular = it
                    if (celular.length == 9) errorCelular = false
                }
            },
            label = { Text(text = "Celular", style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = { Text(text = "+51", modifier = Modifier.padding(start = 16.dp, end = 8.dp), color = Gris, style = MaterialTheme.typography.bodyMedium) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorCelular,
            singleLine = true,
            supportingText = {
                if (errorCelular) {
                    if (celular.isEmpty()) Text(text = "El celular es obligatorio", style = MaterialTheme.typography.labelMedium)
                    else if (celular.length < 9) Text(text = "Debe tener 9 dígitos", style = MaterialTheme.typography.labelMedium)
                } else null
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Correo
        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                if (correo.isNotBlank() && correo.contains("@")) errorCorreo = false
            },
            label = { Text(text = "Correo Electrónico", style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = { Icon(painter = painterResource(R.drawable.ic_mail), contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = errorCorreo,
            singleLine = true,
            supportingText = {
                if (errorCorreo) {
                    if (correo.isEmpty()) Text(text = "El correo es obligatorio", style = MaterialTheme.typography.labelMedium)
                    else if (!correo.contains("@")) Text(text = "Ingrese un correo válido", style = MaterialTheme.typography.labelMedium)
                } else null
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = peso,
                onValueChange = {
                    peso = it
                    if (peso.isNotBlank()) errorPeso = false
                },
                label = { Text(text = "Peso (kg)", style = MaterialTheme.typography.bodyMedium) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorPeso,
                singleLine = true,
                supportingText = { if (errorPeso) Text(text = "Obligatorio", style = MaterialTheme.typography.labelMedium) else null },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = altura,
                onValueChange = {
                    altura = it
                    if (altura.isNotBlank()) errorAltura = false
                },
                label = { Text(text = "Altura (cm)", style = MaterialTheme.typography.bodyMedium) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorAltura,
                singleLine = true,
                supportingText = { if (errorAltura) Text(text = "Obligatorio", style = MaterialTheme.typography.labelMedium) else null },
                modifier = Modifier.weight(1f)
            )
        }

        // Estado Civil
        Box(modifier = Modifier.fillMaxWidth().clickable { mostrarDialogoEstadoCivil = true }) {
            OutlinedTextField(
                value = estadoCivil,
                onValueChange = {},
                label = { Text(text = "Estado Civil", style = MaterialTheme.typography.bodyMedium) },
                readOnly = true,
                enabled = false,
                isError = errorEstadoCivil,
                singleLine = true,
                supportingText = { if (errorEstadoCivil) Text(text = "Selecciona estado civil", style = MaterialTheme.typography.labelMedium) else null },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Blanco,
                    disabledBorderColor = if (errorEstadoCivil) MaterialTheme.colorScheme.error else Gris,
                    disabledLabelColor = if (errorEstadoCivil) MaterialTheme.colorScheme.error else Gris
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (mostrarDialogoEstadoCivil) {
            AlertDialog(
                onDismissRequest = { mostrarDialogoEstadoCivil = false },
                containerColor = Color(0xFF1E1E1E),
                title = { Text(text = "Selecciona tu estado civil", color = Blanco, style = MaterialTheme.typography.bodyLarge) },
                text = {
                    Column {
                        listOf("Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a").forEach { opcion ->
                            TextButton(onClick = {
                                estadoCivil = opcion
                                errorEstadoCivil = false
                                mostrarDialogoEstadoCivil = false
                            }) {
                                Text(text = opcion, color = Celeste, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                },
                confirmButton = {}
            )
        }

        // OTP
        OutlinedTextField(
            value = otp,
            onValueChange = {
                if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                    otp = it
                    if (otp.length == 6) errorOtp = false
                }
            },
            label = { Text(text = "Crear código OTP", style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = { Icon(painter = painterResource(R.drawable.ic_lock), contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { otpVisible = !otpVisible }) {
                    Icon(
                        painter = if (otpVisible) painterResource(R.drawable.ic_hide) else painterResource(R.drawable.ic_show),
                        contentDescription = "Alternar visibilidad",
                        tint = Blanco
                    )
                }
            },
            visualTransformation = if (otpVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            isError = errorOtp,
            singleLine = true,
            supportingText = {
                if (errorOtp) {
                    if (otp.isEmpty()) Text(text = "El OTP es obligatorio", style = MaterialTheme.typography.labelMedium)
                    else if (otp.length != 6) Text(text = "El OTP debe tener exactamente 6 dígitos", style = MaterialTheme.typography.labelMedium)
                } else null
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                errorNombres = nombres.isEmpty()
                errorApPaterno = apPaterno.isEmpty()
                errorApMaterno = apMaterno.isEmpty()
                errorDni = dni.isEmpty() || dni.length < 8
                errorGenero = genero.isEmpty()
                errorCelular = celular.isEmpty() || celular.length < 9
                errorCorreo = correo.isEmpty() || !correo.contains("@")
                errorPeso = peso.isEmpty()
                errorAltura = altura.isEmpty()
                errorEstadoCivil = estadoCivil.isEmpty()

                errorOtp = otp.isEmpty() || otp.length != 6

                if (!errorNombres && !errorApPaterno && !errorApMaterno && !errorDni &&
                    !errorGenero && !errorCelular && !errorCorreo && !errorPeso &&
                    !errorAltura && !errorEstadoCivil && !errorOtp) {

                    sessionManager.saveSession(
                        userId = 1, nombres = nombres, apPaterno = apPaterno, apMaterno = apMaterno,
                        dni = dni, genero = genero, celular = celular, correo = correo,
                        peso = peso, altura = altura, estadoCivil = estadoCivil, otp = otp
                    )
                    Toast.makeText(context, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()
                    onRegisterSuccess()
                } else {
                    Toast.makeText(context, "Por favor, revisa los campos obligatorios y el OTP (6 dígitos)", Toast.LENGTH_LONG).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Azul),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrarse", color = Blanco, style = MaterialTheme.typography.bodyLarge) // Aplicando Quicksand 16.sp[cite: 1]
        }

        TextButton(onClick = onNavigateBack) {
            Text(text = "¿Ya tienes cuenta? Inicia Sesión", color = Celeste, style = MaterialTheme.typography.bodyLarge) // Aplicando Quicksand 16.sp[cite: 1]
        }
    }
}