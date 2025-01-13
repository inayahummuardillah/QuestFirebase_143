package com.example.p9.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.p9.MahasiswaApp

object PenyediaViewModel{
    val Factory =  viewModelFactory {
        initializer {
            HomeViewModel(
                mahasiswaApp().containerApp.MhsRepository
            )
        }
        initializer {
            InsertViewModel(
                mahasiswaApp().containerApp.MhsRepository
            )
        }
    }
}

fun CreationExtras.mahasiswaApp(): MahasiswaApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApp)
