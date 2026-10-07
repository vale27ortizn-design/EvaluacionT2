package com.t2.data.preferences

import android.content.Context

class SessionManager(context: Context) {
    private val preferences = context.getSharedPreferences(
        "dami_t2",
        Context.MODE_PRIVATE
    )

    fun saveSession(
        userId: Int, nombres: String, apPaterno: String, apMaterno: String, dni: String,
        genero: String, celular: String, correo: String, peso: String,
        altura: String, estadoCivil: String, otp: String
    ) {
        preferences.edit()
            .putBoolean("logged", true)
            .putInt("user_id", userId)
            .putString("nombres", nombres)
            .putString("apPaterno", apPaterno)
            .putString("apMaterno", apMaterno)
            .putString("dni", dni)
            .putString("genero", genero)
            .putString("celular", celular)
            .putString("correo", correo)
            .putString("peso", peso)
            .putString("altura", altura)
            .putString("estadoCivil", estadoCivil)
            .putString("otp", otp)
            .apply()
    }
    fun isLogged(): Boolean {
        return preferences.getBoolean("logged", false)
    }

    fun clearSession() {
        preferences.edit()
            .clear()
            .apply()
    }

    // leer datos
    fun getNombres(): String {
        return preferences.getString("nombres", "Usuario") ?: "Usuario"
    }

    fun getCelular(): String {
        return preferences.getString("celular", "") ?: ""
    }

    fun getOtpGuardado(): String {
        return preferences.getString("otp", "") ?: ""
    }
}