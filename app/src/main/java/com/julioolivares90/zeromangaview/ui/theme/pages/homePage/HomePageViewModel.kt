package com.julioolivares90.zeromangaview.ui.theme.pages.homePage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julioolivares90.zeromangaview.data.remote.models.MangaResponse
import com.julioolivares90.zeromangaview.domain.useCases.GetMangasPopulares
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePageViewModel @Inject constructor(val getMangasPopulares: GetMangasPopulares) : ViewModel() {


    private val uiState = mutableStateOf(MangaDataState())

    val state : State<MangaDataState> = uiState

    init {

        getDataFromRepository()
    }

    fun getDataFromRepository() {
        getMangaData()
    }

    private fun getMangaData() {

        viewModelScope.launch(Dispatchers.IO) {
            getMangasPopulares.invoke().collect {manga ->
                when(manga){
                    is MangaResponse.Success -> {
                        uiState.value = uiState.value.copy(value = "", mangasPopulares = manga.data)
                    }
                    is MangaResponse.Loading -> {
                        uiState.value = uiState.value.copy(value = "Loading")
                    }
                    is MangaResponse.Error -> {
                        uiState.value = uiState.value.copy(value =  "Error ${manga.exception.message}")
                    }

                }
            }
        }
    }
}