package com.example.retro_savings_challenge.data.repository

import com.example.retro_savings_challenge.data.local.ChallengeDao
import com.example.retro_savings_challenge.data.local.ParticipationDao
import com.example.retro_savings_challenge.data.local.TransactionDao
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.model.Participation
import com.example.retro_savings_challenge.data.model.Transaction
import kotlinx.coroutines.flow.Flow

class DashboardRepository(
    private val challengeDao: ChallengeDao,
    private val participationDao: ParticipationDao,
    private val transactionDao: TransactionDao
) {
    fun getActiveChallenges(): Flow<List<Challenge>> = challengeDao.getAllChallenges()

    fun getChallengeById(id: String): Flow<Challenge> = challengeDao.getChallengeById(id)

    fun getParticipation(userId: String, challengeId: String): Flow<Participation?> =
        participationDao.getParticipation(userId, challengeId)

    suspend fun joinChallenge(participation: Participation) {
        participationDao.insert(participation)
    }

    suspend fun leaveChallenge(challengeId: String, userId: String) {
        participationDao.delete(challengeId, userId)
    }

    suspend fun saveTransaction(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    fun getTotalSavings(): Flow<Double?> = transactionDao.getTotalSavings()
}
