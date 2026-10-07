package com.t2.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.t2.R
import com.t2.ui.navigation.AppDestinations

@Composable
fun AppBottomBar(
    navController: NavHostController
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    NavigationBar {
        NavigationBarItem(
            selected = currentDestination?.route == AppDestinations.Inicio.route,
            label = {
                Text(
                    text = "Inicio"
                )
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = null,
                )
            },
            onClick = {
                navController.navigate(
                    AppDestinations.Inicio.route
                )
            }
        )
        NavigationBarItem(
            selected = currentDestination?.route == AppDestinations.Perfil.route,
            label = {
                Text(
                    text = "Perfil"
                )
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_perfil),
                    contentDescription = null,
                )
            },
            onClick = {
                navController.navigate(
                    AppDestinations.Perfil.route
                )
            }
        )
    }
}

