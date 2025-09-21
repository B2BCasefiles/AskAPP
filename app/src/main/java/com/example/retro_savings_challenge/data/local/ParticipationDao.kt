package com.example.retro_savings_challenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retro_savings_challenge.data.model.Participation
import kotlinx.coroutines.flow.Flow

@Dao
interface ParticipationDao {

    @Query("SELECT * FROM participations WHERE userId = :userId")
    fun getParticipationsForUser(userId: String): Flow<List<Participation>>

    @Query("SELECT * FROM participations WHERE userId = :userId AND challengeId = :challengeId")
    fun getParticipation(userId: String, challengeId: String): Flow<Participation?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(participation: Participation)

    @Query("DELETE FROM participations WHERE challengeId = :challengeId AND userId = :userId")
    suspend fun delete(challengeId: String, userId: String)

    @Query("UPDATE participations SET progress = :newProgress WHERE id = :participationId")
    suspend fun updateProgress(participationId: String, newProgress: Double)
}
