package com.example.p9.di

import android.content.Context
import com.example.p9.repository.MhsRepository
import com.example.p9.repository.NetworkRepository
import com.google.firebase.firestore.FirebaseFirestore

class MahasiswaContainer (private val context: Context): InterfaceContainerApp {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val MhsRepository: MhsRepository by lazy {
        NetworkRepository(firestore)
    }
}

interface InterfaceContainerApp{
    val MhsRepository: MhsRepository
}