package com.julioolivares90.zeromangaview.domain.repository

import com.julioolivares90.zeromangaview.data.remote.models.Manga

interface MangaRepository {

     fun getMangasPopulares() : MutableList<Manga>
}