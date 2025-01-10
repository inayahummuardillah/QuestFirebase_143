package com.example.p9.repository

import com.example.p9.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkRepository(
    private val firestore: FirebaseFirestore
): MhsRepository {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override fun getAllMahasiswa(): Flow<List<Mahasiswa>> {
        TODO("Not yet implemented")
    }

    override fun getAllMhs(): Flow<List<Mahasiswa>> = callbackFlow {
        //mmebuka collection dari firestore
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)
            .addSnapshotListener{
                    value, error->
                if(value!= null){
                    val mhsList = value.documents.mapNotNull {
                        //convert dari dokument firestore ke data class
                        it.toObject(Mahasiswa::class.java)!!
                    }
                    //fungsi untuk mengirim collection ke dataclass
                    trySend(mhsList)
                }
            }
        awaitClose{
            //menutup collection dari firestore
            mhsCollection.remove()
        }
    }

    override fun getMhs(nim: String): Flow<Mahasiswa> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }
}