package com.example.recyclerview
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity

class DetailActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val entrybutton=findViewById<Button>(R.id.entrybutton)
        entrybutton.setOnClickListener{
            val date=findViewById<EditText>(R.id.DateField).text.toString()
            val BPreading=findViewById<EditText>(R.id.BpField).text.toString()
            val mood=findViewById<EditText>(R.id.moodField).text.toString()

            lifecycleScope.launch(Dispatchers.IO){
                (application as ItemApplication) .db.ItemDAO().insert(
                Item(date,BPreading,mood)
                )
            }

            val i= Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(i)
        }
    }
}