package org.d3if1071.idealku.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataBeratBadanDao {
    @Insert
    fun insert(dataBeratBadan: DataBeratBadan)
    @Update
    fun update(dataBeratBadan: DataBeratBadan)
    @Query("SELECT * FROM dataBeratBadan")
    fun getAllDiary():LiveData<List<DataBeratBadan>>
    @Query("DELETE  FROM dataBeratBadan ")
    fun hapusSemua()
    @Query("Delete from dataBeratBadan where id= :idBeratBadan")
    fun hapus(idBeratBadan: Long)

}