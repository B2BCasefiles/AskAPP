package com.example.retro_savings_challenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.model.Participation
import com.example.retro_savings_challenge.data.model.Transaction

import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Challenge::class, Transaction::class, Participation::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun challengeDao(): ChallengeDao
    abstract fun transactionDao(): TransactionDao
    abstract fun participationDao(): ParticipationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "retro_savings_challenge_database"
                )
                .addCallback(AppDatabaseCallback())
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.challengeDao())
                }
            }
        }

        suspend fun populateDatabase(challengeDao: ChallengeDao) {
            val challenges = listOf(
                Challenge(
                    id = "1",
                    title = "52-Week Savings Challenge",
                    rules = "Save an increasing amount each week, starting with $1.",
                    reward = "A cool badge!",
                    frequency = "Weekly",
                    goalAmount = 1378.0
                ),
                Challenge(
                    id = "2",
                    title = "Round-Up Challenge",
                    rules = "Round up your daily purchases to the nearest dollar and save the change.",
                    reward = "Unlock a new theme!",
                    frequency = "Daily",
                    goalAmount = 100.0
                ),
                Challenge(
                    id = "3",
                    title = "No-Spend Weekend",
                    rules = "Try not to spend any money for an entire weekend.",
                    reward = "Bonus Points!",
                    frequency = "Monthly",
                    goalAmount = 0.0
                )
            )
            challengeDao.insertAll(challenges)
        }
    }
}
