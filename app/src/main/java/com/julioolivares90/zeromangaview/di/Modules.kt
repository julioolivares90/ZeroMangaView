package com.julioolivares90.zeromangaview.di

import com.julioolivares90.zeromangaview.core.Scraper
import com.julioolivares90.zeromangaview.domain.repository.MangaRepository
import com.julioolivares90.zeromangaview.domain.repository.MangarRepositoryImpl
import com.julioolivares90.zeromangaview.domain.useCases.GetMangasPopulares
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.intellij.lang.annotations.PrintFormat

@InstallIn(SingletonComponent::class)
@Module
object ScraperModule {

    @Provides
    fun provideScraper() = Scraper()
}


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideMangaRepository(scraper: Scraper) : MangaRepository {
        return MangarRepositoryImpl(scraper)
    }
}


@InstallIn(SingletonComponent::class)
@Module
object  UseCasesModule {
    @Provides
    fun provideGetMangasPopulares(mangaRepository: MangaRepository): GetMangasPopulares {
        return GetMangasPopulares(mangaRepository)
    }
}