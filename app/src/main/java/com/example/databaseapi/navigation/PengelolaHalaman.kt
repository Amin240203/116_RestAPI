package com.example.databaseapi.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.databaseapi.ui.home.screens.DestinasiHome
import com.example.databaseapi.ui.home.screens.HomeScreen
import com.example.databaseapi.ui.kontak.screen.DestinasiEntry
import com.example.databaseapi.ui.kontak.screen.DetailsDestination
import com.example.databaseapi.ui.kontak.screen.DetailsScreen
import com.example.databaseapi.ui.kontak.screen.EditDestination
import com.example.databaseapi.ui.kontak.screen.EntryKontakScreen
import com.example.databaseapi.ui.kontak.screen.ItemEditScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,

        ){
        composable(DestinasiHome.route){
            HomeScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            },
                onDetailClick = {itemId ->
                    navController.navigate("${DetailsDestination.route}/$itemId")
                    println(itemId)
                })
        }
        composable(DestinasiEntry.route){
            EntryKontakScreen(navigasiBack = {
                navController.navigate(
                    DestinasiHome.route
                ){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.kontakId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DetailsDestination.kontakId)
            itemId?.let {
                DetailsScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { itemId ->
                        navController.navigate("${EditDestination.route}/$itemId")
                        println(itemId)
                    }

                )
            }
        }

        composable(
            EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.kontakId) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
            )
        }
    }
}