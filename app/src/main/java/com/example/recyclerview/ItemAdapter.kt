package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context, private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // listener interface
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }
    class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val itemdate: TextView
        val bptitle: TextView
        val moodtitle: TextView
        val units:TextView

        init {
            itemView.setOnLongClickListener{
                listener.onItemClick(absoluteAdapterPosition)
                true
            }
            // the public final member variables created above
            itemdate=itemView.findViewById(R.id.itemTitle)
            bptitle=itemView.findViewById(R.id.linkTitle)
            moodtitle=itemView.findViewById(R.id.priceTitle)
            units   =itemView.findViewById(R.id.unita)
        }
        fun bind(item: Item){
            units.text="(mmHg)"
            itemdate.text=item.entrydate
            bptitle.text=item.reading
            if(item.mood=="stressed"||item.mood=="mad"){
                moodtitle.text=item.mood
                moodtitle.setTextColor(Color.parseColor("#ffcc0000"))
            }
            else if(item.mood=="tired"||item.mood=="sick"||item.mood=="sad"){
                moodtitle.text=item.mood
                moodtitle.setTextColor(Color.parseColor("#FFA500"))
            }
            else if (item.mood=="good"||item.mood=="happy"||item.mood=="energetic") {
                moodtitle.text = item.mood
                moodtitle.setTextColor(Color.parseColor("#228B22"))
            }
            else if(item.mood=="calm"||item.mood=="relaxed"){
                moodtitle.text=item.mood
                moodtitle.setTextColor(Color.parseColor("#4682B4"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val context = parent.context
        //val inflater = LayoutInflater.from(context)
        val wishlistView = LayoutInflater.from(context).inflate(R.layout.bloodpressure_item,parent,false)
        return ViewHolder(wishlistView,mListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = items[position]
      holder.bind(entry)
    }
}