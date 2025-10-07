package com.example.proyectog5.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.view.setPadding
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddTxDialog(
    private val onConfirm: (amount: Double, category: String, note: String?) -> Unit
) : DialogFragment() {

    private lateinit var refs: Triple<EditText, Spinner, EditText>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Agregar gasto")
            .setView(buildContent())
            .setPositiveButton("Guardar") { dialog, _ ->
                val (amountEt, categorySp, noteEt) = refs
                val amount = amountEt.text.toString().toDoubleOrNull() ?: 0.0
                val category = categorySp.selectedItem?.toString().orEmpty()
                val note = noteEt.text?.toString()
                onConfirm(amount, category, note)
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .create()
    }

    private fun buildContent(): LinearLayout {
        val dp = (16 * resources.displayMetrics.density).toInt()
        val container = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        // Monto
        val tilAmount = TextInputLayout(requireContext())
        val etAmount = TextInputEditText(requireContext()).apply {
            hint = "Monto (ej. 8.50)"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        tilAmount.addView(etAmount)
        container.addView(tilAmount)

        // Categoría
        val spinner = Spinner(requireContext())
        val categories = listOf("Comida", "Transporte", "Servicios", "Salud", "Ocio", "Otros")
        spinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        container.addView(spinner)

        // Nota
        val tilNote = TextInputLayout(requireContext())
        val etNote = TextInputEditText(requireContext()).apply {
            hint = "Nota (opcional)"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        }
        tilNote.addView(etNote)
        container.addView(tilNote)

        refs = Triple(etAmount, spinner, etNote)
        return container
    }
}
