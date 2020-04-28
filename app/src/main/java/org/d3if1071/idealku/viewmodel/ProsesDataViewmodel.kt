package org.d3if1071.idealku.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.d3if1071.idealku.data.DataBeratBadan
import org.d3if1071.idealku.data.DataBeratBadanDao

class ProsesDataViewmodel (val database: DataBeratBadanDao, application: Application): ViewModel() {

    //proses penyimpanan
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val listBeratBadan = database.getAllDiary()

    fun onClickSimpanData(
        berat: String,
        tinggi: String,
        jenisKelamin: String,
        hasilBeratBadan: String,
        hasilBMI: String
    ) {
        uiScope.launch {
            val dataBeratBadan =
                DataBeratBadan(0, berat, tinggi, jenisKelamin, hasilBeratBadan, hasilBMI)
            insert(dataBeratBadan)
        }
    }

    // fungsi insert
    private suspend fun insert(dataBeratBadan: DataBeratBadan) {
        withContext(Dispatchers.IO) {
            database.insert(dataBeratBadan)
        }
    }

    fun onClickHapusSemua() {
        uiScope.launch {
            hapusSemua()
        }
    }

    private suspend fun hapusSemua() {
        withContext(Dispatchers.IO) {
            database.hapusSemua()
        }
    }

    fun onClickUpdate(dataBeratBadan: DataBeratBadan) {
        uiScope.launch {
            update(dataBeratBadan)
        }
    }

    private suspend fun update(dataBeratBadan: DataBeratBadan) {
        withContext(Dispatchers.IO) {
            database.update(dataBeratBadan)
        }
    }

    fun onClickDelete(beratID: Long) {
        uiScope.launch {
            delete(beratID)
        }
    }

    private suspend fun delete(beratID: Long) {
        withContext(Dispatchers.IO) {
            database.hapus(beratID)
        }
    }

}
