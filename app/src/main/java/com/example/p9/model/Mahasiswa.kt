package com.example.p9.model

data class Mahasiswa(
    var nim:String,
    var nama:String,
    var alamat:String,
    var jenisKelamin:String,
    var kelas:String,
    var angkatan:String
){
    constructor(): this("", "", "", "", "", "" )
}
