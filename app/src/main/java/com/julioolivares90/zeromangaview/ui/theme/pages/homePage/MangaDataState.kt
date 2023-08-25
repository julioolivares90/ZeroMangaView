package com.julioolivares90.zeromangaview.ui.theme.pages.homePage

import com.julioolivares90.zeromangaview.data.remote.models.Manga
import com.julioolivares90.zeromangaview.domain.useCases.GetMangasPopulares

data class MangaDataState (var value : String = "" , var mangasPopulares: MutableList<Manga> = mutableListOf()) {

}