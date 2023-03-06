package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val itemtitle: TextView
        val linktitle: TextView
        val pricetitle: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            itemtitle=itemView.findViewById(R.id.itemTitle)
            linktitle=itemView.findViewById(R.id.linkTitle)
            pricetitle=itemView.findViewById(R.id.priceTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val wishlistView = inflater.inflate(R.layout.wishlist_item,parent,false)
        return ViewHolder(wishlistView)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val item = items.get(position)
        holder.itemtitle.text=item.itemname
        holder.linktitle.text=item.link
        holder.pricetitle.text=item.price
    }
}