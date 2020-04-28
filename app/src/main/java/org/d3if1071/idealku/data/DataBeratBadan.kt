package org.d3if1071.idealku.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dataBeratBadan")
data class DataBeratBadan(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    @ColumnInfo(name = "berat")
    var berat:String,
    @ColumnInfo(name = "tinggi")
    var tinggi:String,
    @ColumnInfo(name = "jenisKelamin")
    var jenisKelamin:String,
    @ColumnInfo(name = "hasilBeratBadanIdeal")
    var hasilBeratBadanIdeal:String,
    @ColumnInfo(name = "hasilBMI")
    var hasilBMI:String,
    @ColumnInfo(name = "tanggal")
    var tanggal:Long =System.currentTimeMillis()

)