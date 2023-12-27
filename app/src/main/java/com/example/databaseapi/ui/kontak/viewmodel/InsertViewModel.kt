package com.example.databaseapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaseapi.repositori.KontakRepositori
import kotlinx.coroutines.launch

class InsertViewModel(private val kontakRepositori: KontakRepositori) : ViewModel(){
    var insertKontakState by mutableStateOf(InsertUIState())
        private set
    fun updateInsertKontakState(insertUiEvent: InsertUievent){
        insertKontakState = InsertUIState(insertUiEvent)
    }

    suspend fun insertKontak(){
        viewModelScope.launch {
            try {
                kontakRepositori.insertKontak(insertKontakState.insertUiEvent.toKontak())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

