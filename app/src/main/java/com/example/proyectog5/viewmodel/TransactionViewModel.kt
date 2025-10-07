package com.example.proyectog5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectog5.data.Transaction
import com.example.proyectog5.data.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import androidx.lifecycle.viewModelScope




class TransactionViewModel(private val repo: TransactionRepository) : ViewModel() {

    private val _range = MutableStateFlow(currentMonthRange())
    val range: StateFlow<LongRange> = _range

    val transactions = range.flatMapLatest { r ->
        repo.getBetween(r.first, r.last)
    }.stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    val total = range.flatMapLatest { r ->
        repo.sumBetween(r.first, r.last)
    }.map { it ?: 0.0 }
        .stateIn(viewModelScope, SharingStarted.Companion.Lazily, 0.0)

    fun setRange(from: Long, to: Long) { _range.value = from..to }

    fun add(amount: Double, category: String, note: String?) = viewModelScope.launch {
        repo.add(Transaction(amount = amount, category = category, note = note))
    }

    private fun currentMonthRange(): LongRange {
        val cal = Calendar.getInstance() // El Salvador usa USD → formatearemos luego
        cal.set(Calendar.DAY_OF_MONTH, 1); cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0); cal.set(Calendar.SECOND, 0); cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis
        cal.add(Calendar.MONTH, 1); cal.add(Calendar.MILLISECOND, -1)
        val end = cal.timeInMillis
        return start..end
    }
}