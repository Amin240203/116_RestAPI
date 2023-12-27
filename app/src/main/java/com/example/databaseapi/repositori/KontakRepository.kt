package com.example.databaseapi.repositori

import com.example.databaseapi.model.Kontak
import com.example.databaseapi.service_api.KontakService
import okio.IOException

interface KontakRepositori{
    suspend fun getKontak(): List<Kontak>

    suspend fun insertKontak(kontak: Kontak)

    suspend fun updateKontak(id: Int, kontak: Kontak)

    suspend fun deleteKontak(id: Int)

    suspend fun getkontakById(id: Int): Kontak


}

class NetworkKontakRepositori(
    private val kontakApiService: KontakService
) : KontakRepositori {
    override suspend fun getKontak(): List<Kontak> = kontakApiService.getKontak()

    override suspend fun insertKontak(kontak: Kontak) {
        kontakApiService.insertKontak(kontak)
    }

    override suspend fun updateKontak(id: Int, kontak: Kontak) {
        kontakApiService.updateKontak(id, kontak)
    }

    override suspend fun deleteKontak(id: Int) {
        try {
            val response = kontakApiService.deletekontak(id)
            if (!response.isSuccessful){
                throw IOException("Failed to delete kontak. HTTP status code:"+
                        "${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        }
        catch (e: Exception){
            throw e
        }
    }

    override suspend fun getkontakById(id: Int): Kontak {
        return kontakApiService.getKontakById(id)
    }

}