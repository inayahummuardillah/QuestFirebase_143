package com.example.p9.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p9.model.Mahasiswa
import com.example.p9.repository.MhsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repoMhs: MhsRepository
): ViewModel() {

    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init { //menjalankan
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            repoMhs.getAllMhs(). onStart {
                mhsUiState = HomeUiState.Loading
            }
                .catch {
                    mhsUiState = HomeUiState.Error( e = it)
                }
                .collect{
                    mhsUiState = if(it.isEmpty()){
                        HomeUiState.Error(Exception("Belum ada data mahasiswa"))
                    } else {
                        HomeUiState.Succes(it)
                    }
                }
        }
    }
}

sealed class HomeUiState {
    //Loading
    object Loading : HomeUiState()
    //Sukses
    data class Succes( val data: List<Mahasiswa>) : HomeUiState()
    //Eror
    data class Error( val e: Throwable) : HomeUiState()
}