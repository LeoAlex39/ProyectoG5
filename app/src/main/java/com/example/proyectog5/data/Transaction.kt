package com.example.proyectog5.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,                // positivo = gasto (o maneja signo)
    val category: String,              // "Comida", "Transporte", etc.
    val note: String? = null,
    val timestamp: Long = Date().time   // millis
)