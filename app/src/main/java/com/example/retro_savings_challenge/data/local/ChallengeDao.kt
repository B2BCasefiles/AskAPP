package com.example.retro_savings_challenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retro_savings_challenge.data.model.Challenge
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenges")
    fun getAllChallenges(): Flow<List<Challenge>>

    @Query("SELECT * FROM challenges WHERE id = :id")
    fun getChallengeById(id: String): Flow<Challenge>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(challenges: List<Challenge>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(challenge: Challenge)

    @Query("DELETE FROM challenges")
    suspend fun deleteAll()
}
