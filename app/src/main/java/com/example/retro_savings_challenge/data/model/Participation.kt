package com.example.retro_savings_challenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participations")
data class Participation(
    @PrimaryKey val id: String,
    val userId: String,
    val challengeId: String,
    val progress: Double,
    val lastUpdate: Long
)
