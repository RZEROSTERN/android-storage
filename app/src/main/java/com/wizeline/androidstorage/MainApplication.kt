package com.wizeline.androidstorage

import android.app.Application
import com.wizeline.androidstorage.data.AppDatabase

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}