package com.example.proyectog5.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tx: Transaction)

    @Delete
    suspend fun delete(tx: Transaction)

    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAll(): Flow<List<Transaction>>

    @Query("""
        SELECT * FROM transactions 
        WHERE timestamp BETWEEN :from AND :to 
        ORDER BY timestamp DESC
    """)
    fun getBetween(from: Long, to: Long): Flow<List<Transaction>>

    @Query("SELECT SUM(amount) FROM transactions WHERE timestamp BETWEEN :from AND :to")
    fun sumBetween(from: Long, to: Long): Flow<Double?>
}