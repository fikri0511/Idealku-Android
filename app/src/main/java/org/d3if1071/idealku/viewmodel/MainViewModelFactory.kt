package org.d3if1071.idealku.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if1071.idealku.data.DataBeratBadanDao

class MainViewModelFactory(
    private val dataSource: DataBeratBadanDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProsesDataViewmodel::class.java)) {
            return ProsesDataViewmodel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}