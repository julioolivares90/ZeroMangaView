package com.julioolivares90.zeromangaview.data.remote.models

import java.lang.Exception

sealed class MangaResponse<out R> {

    object Loading : MangaResponse<Nothing>()

    data class Success<out T>(val data : T) : MangaResponse<T>()

    data class Error(val exception: Exception) :MangaResponse<Nothing>()
}