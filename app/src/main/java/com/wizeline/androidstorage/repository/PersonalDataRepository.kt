package com.wizeline.androidstorage.repository

import com.wizeline.androidstorage.MainApplication
import com.wizeline.androidstorage.data.AppDatabase
import com.wizeline.androidstorage.data.model.PersonalData

class PersonalDataRepository(application: MainApplication) {
    private val dao = AppDatabase.getInstance(application).personalDataDao()

    suspend fun getData(email: String): PersonalData = dao.getProfileByID(email)

    suspend fun insertData(personalData: PersonalData): Long = dao.insertAll(personalData)

    suspend fun deleteData(email: String): Int = dao.deleteByUserId(email)
}