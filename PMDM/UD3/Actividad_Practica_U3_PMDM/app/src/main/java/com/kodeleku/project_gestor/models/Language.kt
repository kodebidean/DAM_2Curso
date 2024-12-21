package com.kodeleku.project_gestor.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class Language(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)