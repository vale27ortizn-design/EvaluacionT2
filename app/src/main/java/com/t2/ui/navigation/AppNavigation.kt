package com.t2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.t2.data.preferences.SessionManager
import com.t2.ui.screens.InicioScreen
import com.t2.ui.screens.LoginScreen
import com.t2.ui.screens.PerfilScreen
import com.t2.ui.screens.RegisterScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    NavHost(
        navController = navController,
        startDestination = if (sessionManager.isLogged()) {
            AppDestinations.Inicio.route
        } else {
            AppDestinations.Login.route
        },
        modifier = modifier,
    ) {
        composable(AppDestinations.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // reutilizado de serena
                    navController.navigate(AppDestinations.Inicio.route) {
                        popUpTo(AppDestinations.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(AppDestinations.Registro.route)
                }
            )
        }
        composable(AppDestinations.Inicio.route) {
            InicioScreen()
        }
        composable(AppDestinations.Registro.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.popBackStack()
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(AppDestinations.Perfil.route) {
            PerfilScreen(
                onLogout = {
                    navController.navigate(AppDestinations.Login.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}