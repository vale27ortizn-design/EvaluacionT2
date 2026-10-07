package com.t2.ui.navigation

sealed class AppDestinations(
    val route: String
) {
    data object Inicio: AppDestinations("inicio")
    data object Login: AppDestinations("login")
    data object Registro: AppDestinations("registro")
    data object Perfil: AppDestinations("perfil")
}
