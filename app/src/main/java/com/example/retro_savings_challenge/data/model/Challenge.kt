package com.example.retro_savings_challenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class Challenge(
    @PrimaryKey val id: String,
    val title: String,
    val rules: String,
    val reward: String,
    val frequency: String,
    val goalAmount: Double
)
