package com.example.p9.repository

import com.example.p9.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface MhsRepository {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>

    fun getMhs(nim: String): Flow<Mahasiswa>

    suspend fun deleteMhs(Mahasiswa: Mahasiswa)

    suspend fun updateMhs(Mahasiswa: Mahasiswa)
    fun getAllMhs(): Flow<List<Mahasiswa>>
}