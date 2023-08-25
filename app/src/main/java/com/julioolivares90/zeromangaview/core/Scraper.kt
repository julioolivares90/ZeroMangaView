package com.julioolivares90.zeromangaview.core

import com.julioolivares90.zeromangaview.data.remote.models.Manga
import com.julioolivares90.zeromangaview.utilidades.BASE_URL
import com.julioolivares90.zeromangaview.utilidades.MANGAS_POPULARES
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

class Scraper {

    fun getMangasPopulares () : MutableList<Manga>{

        val mangasPopulares = mutableListOf<Manga>()

        try {

            val client = OkHttpClient()

            val request = Request.Builder()
                .url(BASE_URL + MANGAS_POPULARES)
                .build()

            val response = client.newCall(request).execute()

            val doc = Jsoup.parse(response.body!!.string())
            //parser de mangas
            println(doc.body().html())

            val divPrincipal = doc.select("#app > main > div:nth-child(2) > div.col-12.col-lg-8.col-xl-9 > div:nth-child(1)")
                .first()

            val mangaItems = divPrincipal?.select("div.element")

            if (mangaItems != null) {
                for (item in mangaItems){
                    val manga = Manga(
                        title = item.select("a > div > div > h4").first()!!.text(),
                        demography = item.select("a > div > span.demography").first()!!.text(),
                        mangaUrl = item.select("a").first()!!.attr("href"),
                        mangaImagen = getImageFromMangaUrl(item.select("a > div > style").first()!!.html()),
                        type = item.select("a > div > span.book-type").first()!!.text(),
                        score = item.select("a > div > span.score > span").first()!!.text()

                    )

                    mangasPopulares.add(manga)
                }
            }
            return  mangasPopulares
        }
        catch (ex : Exception){
         return mangasPopulares
        }

    }
}