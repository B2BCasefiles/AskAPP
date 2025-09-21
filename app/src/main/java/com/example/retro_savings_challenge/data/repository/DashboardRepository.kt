package com.example.retro_savings_challenge.data.repository

import com.example.retro_savings_challenge.data.local.ChallengeDao
import com.example.retro_savings_challenge.data.model.Challenge
import kotlinx.coroutines.flow.Flow

class DashboardRepository(private val challengeDao: ChallengeDao) {
    fun getActiveChallenges(): Flow<List<Challenge>> {
        return challengeDao.getAllChallenges()
    }
}
