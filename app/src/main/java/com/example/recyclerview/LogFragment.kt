package com.example.recyclerview

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LogFragment : Fragment() {
    var itemslist: MutableList<Item> = mutableListOf()
    private lateinit var RVBPlayout: RecyclerView
    private lateinit var adapter: ItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_log, container, false)

        //database
        lifecycleScope.launch {
            (requireActivity().application as ItemApplication).db.ItemDAO().getAll().collect { databaseList ->
                databaseList.map { mappedList ->
                    itemslist.addAll(listOf(mappedList))
                    adapter.notifyDataSetChanged()
                }
            }
        }


        RVBPlayout=view.findViewById(R.id.log_recycler_view)
        adapter = ItemAdapter(requireContext(), itemslist)
        RVBPlayout.adapter = adapter
        RVBPlayout.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            RVBPlayout.addItemDecoration(dividerItemDecoration)
        }

        val addbutton = view.findViewById<Button>(R.id.addbutton)

        addbutton.setOnClickListener {
            val i = Intent(requireActivity(), DetailActivity::class.java)
            startActivity(i)
        }

        val deletebutton = view.findViewById<Button>(R.id.deletebutton)
        deletebutton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (requireActivity().application as ItemApplication).db.ItemDAO().deleteAll()
            }
            itemslist.clear()
           adapter.notifyDataSetChanged()
        }

        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    requireContext(),
                    "Item removed at position $position",
                    Toast.LENGTH_LONG
                ).show()
                itemslist.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        })
        // Inflate the layout for this fragment
        return view

    }

    companion object {
   fun newInstance(): LogFragment{
       return LogFragment()
   }
    }
}