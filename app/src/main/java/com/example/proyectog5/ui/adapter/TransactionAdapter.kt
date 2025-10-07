package com.example.proyectog5.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyectog5.data.Transaction
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TxVH>() {

    private val items = mutableListOf<Transaction>()

    fun submit(list: List<Transaction>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TxVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TxVH(v)
    }

    override fun onBindViewHolder(holder: TxVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class TxVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        private val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        private val tvNote: TextView = view.findViewById(R.id.tvNote)
        private val tvDate: TextView = view.findViewById(R.id.tvDate)

        fun bind(tx: Transaction) {
            tvCategory.text = tx.category
            tvAmount.text = formatCurrency(tx.amount)
            tvNote.text = tx.note.orEmpty()
            tvDate.text = formatDate(tx.timestamp)
        }

        private fun formatCurrency(value: Double): String {
            val nf = NumberFormat.getCurrencyInstance(Locale.US) // USD
            return nf.format(value)
        }

        private fun formatDate(millis: Long): String {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            return sdf.format(Date(millis))
        }
    }
}
