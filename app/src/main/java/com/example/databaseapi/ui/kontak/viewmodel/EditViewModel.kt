package com.example.databaseapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaseapi.repositori.KontakRepositori
import com.example.databaseapi.ui.kontak.screen.EditDestination
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val kontakRepositori: KontakRepositori
): ViewModel(){
    var editKontakState by mutableStateOf(InsertUIState())
        private set
    val kontakId: Int = checkNotNull(savedStateHandle[EditDestination.kontakId])

    init {
        viewModelScope.launch {
            editKontakState = kontakRepositori.getkontakById(kontakId).toUiStateKontak()
        }
    }
    fun updateInsertKontakState(insertUiEvent: InsertUiEvent) {
        editKontakState = InsertUIState(insertUiEvent = insertUiEvent)
    }
    fun updateKontak(){
        viewModelScope.launch {
            try {
                kontakRepositori.updateKontak(kontakId, editKontakState.insertUiEvent.toKontak())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}