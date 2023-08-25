package com.julioolivares90.zeromangaview.domain.repository

import com.julioolivares90.zeromangaview.core.Scraper
import com.julioolivares90.zeromangaview.data.remote.models.Manga

class MangarRepositoryImpl constructor(private val scraper: Scraper) : MangaRepository {

    override fun getMangasPopulares() : MutableList<Manga> {

        return scraper.getMangasPopulares()
    }
}