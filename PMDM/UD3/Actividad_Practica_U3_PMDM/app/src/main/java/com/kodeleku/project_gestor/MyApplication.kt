package com.kodeleku.project_gestor

import android.app.Application
import androidx.room.Room
import com.kodeleku.project_gestor.data.AppDatabase

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Inicializar Room Database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "gestor_proyectos_db" // Nombre de la base de datos
        ).fallbackToDestructiveMigration() // Permite migraciones destructivas para evitar problemas de versión inicial
            .build()
    }
}