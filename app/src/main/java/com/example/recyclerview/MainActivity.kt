package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
     var itemslist: MutableList<Item> = mutableListOf()
    private lateinit var RVBPlayout:RecyclerView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        RVBPlayout=findViewById<RecyclerView>(R.id.WishListRV)
        // items equal to something
        val adapter =ItemAdapter(this,itemslist)
        RVBPlayout.adapter=adapter

        //database
        lifecycleScope.launch{
            (application as ItemApplication). db.ItemDAO().getAll().collect{databaseList->
                databaseList.map{mappedList->
                    itemslist.addAll(listOf(mappedList))
                    adapter.notifyDataSetChanged()
                }
            }
        }



        RVBPlayout.layoutManager=LinearLayoutManager(this).also {
            val dividerItemDecoration=DividerItemDecoration(this,it.orientation)
            RVBPlayout.addItemDecoration(dividerItemDecoration)
        }

        val addbutton=findViewById<Button>(R.id.addbutton)

        addbutton.setOnClickListener{
           val i= Intent(this@MainActivity,DetailActivity::class.java)
            startActivity(i)
        }

        val deletebutton=findViewById<Button>(R.id.deletebutton)
        deletebutton.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                (application as ItemApplication) .db.ItemDAO().deleteAll()
            }
            itemslist.clear()
            finish()
            startActivity(getIntent())
        }

        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener{
            override  fun onItemClick(position: Int){
                Toast.makeText(this@MainActivity, "Item removed at position $position", Toast.LENGTH_LONG).show()
                itemslist.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        })

    }
}