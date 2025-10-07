package com.example.proyectog5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectog5.ui.adapter.TransactionAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var adapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rv = RecyclerView(requireContext())
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = TransactionAdapter()
        rv.adapter = adapter
        return rv
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm = (requireActivity() as MainActivity).vm()

        viewLifecycleOwner.lifecycleScope.launch {
            vm.transactions.collectLatest { list ->
                adapter.submit(list)
            }
        }
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
