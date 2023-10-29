package com.julioolivares90.zeromangaview.navigation

sealed class Screen (val route : String){
    object HomePage :Screen(route = "home_page")
    object FavoritosPage : Screen(route = "favoritos_page")

    object BusquedaPage : Screen(route = "busqueda_page")

    object DetailPage : Screen(route = "detail_page")
}