package com.julioolivares90.zeromangaview.domain.useCases

import com.julioolivares90.zeromangaview.data.remote.models.Manga
import com.julioolivares90.zeromangaview.data.remote.models.MangaResponse
import com.julioolivares90.zeromangaview.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMangasPopulares @Inject constructor(private val mangaRepository: MangaRepository) {

    suspend fun invoke():Flow<MangaResponse<MutableList<Manga>>> = flow {
        emit(MangaResponse.Loading)
        try {
            val mangasPopulares = mangaRepository.getMangasPopulares()
            emit(MangaResponse.Success(mangasPopulares))
        }catch (ex : Exception){
            emit(MangaResponse.Error(ex))
        }
    }
}