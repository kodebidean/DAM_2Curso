package com.kodeleku.project_gestor.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.kodeleku.project_gestor.models.Language

@Dao
interface LanguageDao {

    @Insert
    suspend fun insertLanguage(language: Language)

    @Query("SELECT * FROM languages")
    suspend fun getAllLanguages(): List<Language>

    @Query("SELECT * FROM languages WHERE id = :id")
    suspend fun getLanguageById(id: Int): Language?

    @Delete
    suspend fun deleteLanguage(language: Language)
}
