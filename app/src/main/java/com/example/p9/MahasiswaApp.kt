package com.example.p9

import android.app.Application
import com.example.p9.di.MahasiswaContainer

class MahasiswaApp : Application() {
    //Fungsinya untuk menyimpan instance containerApp
    lateinit var containerApp: MahasiswaContainer

    override fun onCreate() {
        super.onCreate()
        //membuat instance containerApp
        containerApp = MahasiswaContainer(this)
        //instance adalah yang dibuat dari class
    }
}