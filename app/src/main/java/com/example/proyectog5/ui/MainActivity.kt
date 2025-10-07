package com.example.proyectog5.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto.R
import com.example.proyectog5.ui.dialogs.AddTxDialog
import com.example.proyectog5.viewmodel.TransactionViewModel
import com.example.proyectog5.viewmodel.VMFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val vm by lazy {
        ViewModelProvider(this, VMFactory(this))[TransactionViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commit()
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            AddTxDialog { amount, category, note ->
                vm.add(amount, category, note)
            }.show(supportFragmentManager, "AddTxDialog")
        }
    }

    fun vm(): TransactionViewModel = vm
}
