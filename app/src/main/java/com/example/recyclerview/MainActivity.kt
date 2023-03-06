package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
     var items: MutableList<Item> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val RVlayout=findViewById<RecyclerView>(R.id.WishListRV)
        // items equal to something
        val adapter =ItemAdapter(items)
        RVlayout.adapter=adapter
        RVlayout.layoutManager=LinearLayoutManager(this)
        val iteminput=findViewById<EditText>(R.id.iteminput)
        val linkinput=findViewById<EditText>(R.id.linkinput)
        val priceinput=findViewById<EditText>(R.id.priceinput)
        val addbutton=findViewById<Button>(R.id.addbutton)

        addbutton.setOnClickListener{
            val name=iteminput.text.toString()
            val link=linkinput.text.toString()
            val price=priceinput.text.toString()
            val newItem=Item(name,link,price)

            items.add(newItem)
            adapter.notifyDataSetChanged()
        }

    }
}