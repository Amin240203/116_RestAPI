package com.example.databaseapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaseapi.model.Kontak
import com.example.databaseapi.repositori.KontakRepositori
import com.example.databaseapi.ui.kontak.screen.DetailsDestination
import kotlinx.coroutines.launch

sealed class DetailsKontakUiState{
    data class Succes(
        val kontak: Kontak) : DetailsKontakUiState()

    object Error : DetailsKontakUiState()
    object Loading : DetailsKontakUiState()
}

class DetailsViewModel (
    savedStateHandle: SavedStateHandle,
    private val kontakRepositori: KontakRepositori
) : ViewModel(){

    private val kontakId: Int = checkNotNull(savedStateHandle[DetailsDestination.kontakId])
    var detailsKontakUiState: DetailsKontakUiState by mutableStateOf(DetailsKontakUiState.Loading)
        private set
    init {
        getKontakById()
    }
    fun getKontakById(){
        viewModelScope.launch {
            detailsKontakUiState = DetailsKontakUiState.Loading
            detailsKontakUiState = try {
                DetailsKontakUiState.Succes(
                    kontak = kontakRepositori.getkontakById(kontakId)
                )
            }catch (e: Exception){
                DetailsKontakUiState.Error
            }
        }
    }
}