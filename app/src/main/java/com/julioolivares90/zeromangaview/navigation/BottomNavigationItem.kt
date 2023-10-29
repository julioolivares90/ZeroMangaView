package com.julioolivares90.zeromangaview.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem (val titulo : String ,
                                 val selectedIcon : ImageVector,
                                 val unselectIcon : ImageVector,
                                 val route : String)