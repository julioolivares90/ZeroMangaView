package com.julioolivares90.zeromangaview.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.julioolivares90.zeromangaview.ui.theme.pages.homePage.HomePage
import com.julioolivares90.zeromangaview.ui.theme.pages.homePage.HomePageViewModel
import com.julioolivares90.zeromangaview.ui.theme.pages.detailPage.DetailPage

@Composable
fun SetUpNavController(navHostController: NavHostController,viewModel: HomePageViewModel){
    NavHost(navController = navHostController, startDestination = Screen.HomePage.route ){
        composable(
            route = Screen.HomePage.route
        ){
            HomePage(viewModel = viewModel,navHostController)
        }
        composable(route = Screen.DetailPage.route){
            DetailPage(navHostController)
        }
    }
}