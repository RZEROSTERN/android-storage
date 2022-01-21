package com.wizeline.androidstorage.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.wizeline.androidstorage.data.dao.PersonalDataDao
import com.wizeline.androidstorage.data.model.PersonalData

@Database(
    entities = [PersonalData::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personalDataDao(): PersonalDataDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return buildDatabase(context)
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "wizeliners-db").build()
        }
    }
}