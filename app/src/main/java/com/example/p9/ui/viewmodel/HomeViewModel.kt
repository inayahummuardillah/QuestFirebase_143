package com.example.p9.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p9.model.Mahasiswa
import com.example.p9.repository.MhsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: MhsRepository
) : ViewModel(){

    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs(){
        viewModelScope.launch {
            repository.getAllMahasiswa().onStart {
                mhsUIState = HomeUiState.Loading
            }
                .catch {
                    mhsUIState = HomeUiState.Error(e = it)
                }
                .collect {
                    mhsUIState = if (it.isEmpty()){
                        HomeUiState.Error(Exception("Belum ada data mahsiswa"))
                    } else {
                        HomeUiState.Success(it)
                    }
                }
        }
    }

    fun deleteMhs(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            try { repository.deleteMhs(mahasiswa)
            } catch (e: Exception) {
                mhsUIState = HomeUiState.Error(e)
            }
        }
    }
}
sealed class HomeUiState{
    //loading
    object Loading : HomeUiState()
    //sukses
    data class Success(val data: List<Mahasiswa>) : HomeUiState()
    //error
    data class Error(val e: Throwable) : HomeUiState()
}
