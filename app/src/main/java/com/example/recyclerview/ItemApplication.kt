package com.example.recyclerview

import android.app.Application

class ItemApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}