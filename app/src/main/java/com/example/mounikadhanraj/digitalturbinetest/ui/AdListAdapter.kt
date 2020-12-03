package com.example.mounikadhanraj.digitalturbinetest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mounikadhanraj.digitalturbinetest.R
import com.example.mounikadhanraj.digitalturbinetest.model.AdDetails
import com.squareup.picasso.Picasso

class AdListAdapter(val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<AdListAdapter.MyViewHolder>()
{
    private var adList: ArrayList<AdDetails> = ArrayList()

    fun loadDetails(adDetails: List<AdDetails>) {
        adList.clear()
        adList.addAll(adDetails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val adName = adList[position].productName
        holder.adName.text = adName

        holder.item_rating.text = adList[position].rating

        Picasso.get()
            .load(adList[position].productThumbnail)
            .into(holder.icon)

        holder.itemView.setOnClickListener {
            onItemClickListener.onitemClickedListener(adList[position], position) }
    }

    override fun getItemCount(): Int {
        return adList.size
    }

    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)
    {
        val icon: ImageView
        val adName: TextView
        val item_rating:TextView

        init {
            this.icon = itemView.findViewById(R.id.icon) as ImageView
            this.adName = itemView.findViewById(R.id.product_name) as TextView
            this.item_rating=itemView.findViewById(R.id.item_rating)as TextView
        }
    }


}