package com.kodeleku.project_gestor.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val hours: Int,
    val priority: Int,
    val description: String,
    val startDate: Long,
    val languageId: Int // Relación con la tabla Language
)
