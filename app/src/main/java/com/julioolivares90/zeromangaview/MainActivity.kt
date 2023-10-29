package com.julioolivares90.zeromangaview

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.inputmethod.InputContentInfoCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.julioolivares90.zeromangaview.domain.useCases.GetMangasPopulares
import com.julioolivares90.zeromangaview.navigation.BottomNavigationItem
import com.julioolivares90.zeromangaview.navigation.Screen
import com.julioolivares90.zeromangaview.navigation.SetUpNavController
import com.julioolivares90.zeromangaview.ui.theme.Purple40
import com.julioolivares90.zeromangaview.ui.theme.ZeroMangaViewTheme
import com.julioolivares90.zeromangaview.ui.theme.pages.busquedaPage.BusquedaPage
import com.julioolivares90.zeromangaview.ui.theme.pages.detailPage.DetailPage
import com.julioolivares90.zeromangaview.ui.theme.pages.favoritosPage.FavoritosPage
import com.julioolivares90.zeromangaview.ui.theme.pages.homePage.HomePage
import com.julioolivares90.zeromangaview.ui.theme.pages.homePage.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var homePageViewModel : HomePageViewModel

    lateinit var navController : NavHostController

    val items = listOf(
        BottomNavigationItem(
            titulo = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectIcon = Icons.Outlined.Home,
            route = Screen.HomePage.route
        ),
        BottomNavigationItem(
            titulo = "Busqueda",
            selectedIcon = Icons.Filled.Search,
            unselectIcon = Icons.Outlined.Search,
            route = Screen.BusquedaPage.route
        ),
    BottomNavigationItem(
            titulo = "Favoritos",
            selectedIcon = Icons.Filled.Favorite,
            unselectIcon = Icons.Outlined.Favorite,
            route = Screen.FavoritosPage.route
        )
    )
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ZeroMangaViewTheme {
                isSystemInDarkTheme()
                navController = rememberNavController()

                //SetUpNavController(navHostController = navController, viewModel = homePageViewModel)

                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Scaffold(
                    
                    bottomBar =  {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEachIndexed {index,screen ->
                                BottomNavigationItem(
                                    modifier = Modifier.background(Purple40),
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    label = { Text(text = screen.titulo, color = Color.White) },
                                    onClick = {

                                        selectedItemIndex = index;
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }

                                            // Avoid multiple copies of the same destination when
                                            // reelecting the same item
                                            launchSingleTop = true

                                            // Restore state when reelecting a previously selected item

                                            restoreState = true
                                        }
                                    },
                                    icon = { Icon(imageVector = if (index == selectedItemIndex) screen.selectedIcon else screen.unselectIcon
                                        , contentDescription = "Icono de bottomBar") },
                                )
                            }
                        }
                    },
                    /*topBar = {
                        TopAppBar(title = { Text(text = "Inicio" , color = Color.White)  }, backgroundColor = Purple40)
                    }

                     */
                ){innerPadding ->
                    NavHost(navController = navController, startDestination = Screen.HomePage.route,Modifier.padding(innerPadding)){
                        composable(Screen.HomePage.route){
                            HomePage(viewModel = homePageViewModel, navHostController = navController)
                        }
                        composable(Screen.BusquedaPage.route){
                            BusquedaPage(navController)
                        }
                        composable(Screen.FavoritosPage.route){
                            FavoritosPage()
                        }
                        composable(route = Screen.DetailPage.route){
                            DetailPage(navHostController = navController)
                        }
                    }
                }
                
                // A surface container using the 'background' color from the theme
                /*
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    //HomePage(viewModel = homePageViewModel)
                }
                */

            }
        }
    }
}

