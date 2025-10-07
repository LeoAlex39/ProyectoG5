package com.example.proyectog5.data

class TransactionRepository(private val dao: TransactionDao) {
    fun getAll() = dao.getAll()
    fun getBetween(from: Long, to: Long) = dao.getBetween(from, to)
    fun sumBetween(from: Long, to: Long) = dao.sumBetween(from, to)
    suspend fun add(tx: Transaction) = dao.insert(tx)
    suspend fun remove(tx: Transaction) = dao.delete(tx)
}