package com.example.databaseapi.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.databaseapi.KontakApplication
import com.example.databaseapi.ui.home.viewmodel.HomeViewModel
import com.example.databaseapi.ui.kontak.viewmodel.DetailsViewModel
import com.example.databaseapi.ui.kontak.viewmodel.EditViewModel
import com.example.databaseapi.ui.kontak.viewmodel.InsertViewModel

object PenyediaViewModel {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiKontak().container.kontakRepositori)
        }

        initializer {
            InsertViewModel(aplikasiKontak().container.kontakRepositori)
        }

        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                kontakRepositori = aplikasiKontak().container.kontakRepositori
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                kontakRepositori = aplikasiKontak().container.kontakRepositori
            )
        }
    }
}

fun CreationExtras.aplikasiKontak(): KontakApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakApplication)