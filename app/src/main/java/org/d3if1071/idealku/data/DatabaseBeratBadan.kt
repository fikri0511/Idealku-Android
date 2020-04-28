package org.d3if1071.idealku.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataBeratBadan::class],version = 1,exportSchema = false)
abstract class DatabaseBeratBadan :RoomDatabase(){
    abstract val dataBeratBadanDao : DataBeratBadanDao
    companion object{
        @Volatile
        private var INSTANCE:DatabaseBeratBadan?=null

        fun getInstance(context: Context):DatabaseBeratBadan{
            synchronized(this){
                var instance = INSTANCE

                if (instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,DatabaseBeratBadan::class.java,"beratBadan_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance

            }
        }

    }

}