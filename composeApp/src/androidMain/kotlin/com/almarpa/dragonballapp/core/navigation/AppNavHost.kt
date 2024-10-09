package com.almarpa.dragonballapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.almarpa.dragonballapp.ui.detail.DetailScreen
import com.almarpa.dragonballapp.ui.home.HomeScreen

@Composable
fun AppNavHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home){
        composable<Routes.Home>{
            HomeScreen( navigateToDetail = { id ->
                navController.navigate(Routes.Detail(id = id))
            })
        }
        
        composable<Routes.Detail>{ navBackStackEntry ->
            val detail: Routes.Detail = navBackStackEntry.toRoute<Routes.Detail>()
            DetailScreen(id = detail.id, navigateBack = {
                navController.popBackStack()
            })
        }
    }
}