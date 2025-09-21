package com.example.retro_savings_challenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id: String,
    val userId: String,
    val amount: Double,
    val date: Long,
    val type: String
)
