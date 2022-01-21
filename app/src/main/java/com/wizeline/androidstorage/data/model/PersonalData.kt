package com.wizeline.androidstorage.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profiles")
data class PersonalData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("first_name")
    val first_name: String,
    @field:SerializedName("last_name")
    val last_name: String,
    @field:SerializedName("phone")
    val phone: String
)