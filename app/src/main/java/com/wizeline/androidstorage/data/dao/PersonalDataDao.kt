package com.wizeline.androidstorage.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wizeline.androidstorage.data.model.PersonalData

@Dao
interface PersonalDataDao {
    @Query("SELECT * FROM profiles where email LIKE :email")
    suspend fun getProfileByID(email: String): PersonalData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(personalData: PersonalData): Long

    @Query("DELETE FROM profiles WHERE email = :email")
    suspend fun deleteByUserId(email: String): Int
}
