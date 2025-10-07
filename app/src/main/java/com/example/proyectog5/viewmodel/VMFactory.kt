package com.example.proyectog5.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectog5.data.AppDatabase
import com.example.proyectog5.data.TransactionRepository

@Suppress("UNCHECKED_CAST")
class VMFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Instancias mínimas para armar el ViewModel
        val db = AppDatabase.get(context.applicationContext)
        val repo = TransactionRepository(db.transactionDao())

        return when {
            modelClass.isAssignableFrom(TransactionViewModel::class.java) ->
                TransactionViewModel(repo) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
