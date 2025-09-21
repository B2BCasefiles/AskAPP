package com.example.retro_savings_challenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.model.Participation
import com.example.retro_savings_challenge.data.model.Transaction

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
                // In a real app, you'd add migrations here.
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
