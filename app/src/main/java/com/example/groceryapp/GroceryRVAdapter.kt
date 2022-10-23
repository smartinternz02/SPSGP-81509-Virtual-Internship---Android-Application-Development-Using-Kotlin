package com.example.groceryapp

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryRVAdapter(
    var list: List<GroceryItems>,
    val groceryItemClickInterface: GroceryItemClickInterface)
    : RecyclerView.Adapter<GroceryRVAdapter.GroceryViewHolder>(), Parcelable {

    inner class GroceryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        val totalTV = itemView.findViewById<TextView>(R.id.idtvtotalamount)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idTVDelete)
    }


    interface GroceryItemClickInterface{
        fun onItemClick(groceryItems: GroceryItems)
    }

    constructor(parcel: Parcel) : this(
        TODO("list"),
        TODO("groceryItemClickInterface")
    ) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rev_item,parent,false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "₹: "+list.get(position).itemPrice.toString()
        val itemTotal :Int = list.get(position).itemQuantity * list.get(position).itemPrice
        holder.totalTV.text = "₹: "+itemTotal.toString()
        holder.deleteIV.setOnClickListener {
            groceryItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GroceryRVAdapter> {
        override fun createFromParcel(parcel: Parcel): GroceryRVAdapter {
            return GroceryRVAdapter(parcel)
        }

        override fun newArray(size: Int): Array<GroceryRVAdapter?> {
            return arrayOfNulls(size)
        }
    }
}