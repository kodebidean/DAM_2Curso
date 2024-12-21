package com.kodeleku.project_gestor.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kodeleku.project_gestor.models.Language
import com.kodeleku.project_gestor.models.Project


@Database(entities = [Language::class, Project::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun languageDao(): LanguageDao
    abstract fun projectDao(): ProjectDao
}
