package com.rawtooth.yourride.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rawtooth.yourride.R
import com.rawtooth.yourride.model.ridemodel.RidesModelItem

class Adapter(val context:Context):RecyclerView.Adapter<Adapter.rideViewAdapter>(){

    private var rideList: List<RidesModelItem>? = null

    fun setRideList(rideList: List<RidesModelItem>?) {
        this.rideList = rideList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rideViewAdapter {
       val view= LayoutInflater.from(context).inflate(R.layout.item_card,parent,false)
        return rideViewAdapter(view)
    }

    override fun onBindViewHolder(holder: rideViewAdapter, position: Int) {
        val article= rideList?.get(position)
        if (article != null) {
            holder.id.text="Ride id: "+article.id.toString()
            holder.city.text=article.city
            holder.state.text=article.state
            holder.distance.text= "Distance: "+article.difference.toString()
            Glide.with(context).load(article.map_url).into(holder.image)
            holder.path.text="Station path: "+article.station_path.toString()
            holder.time.text="Date: "+article.date
            holder.origin.text="Origin: "+article.origin_station_code.toString()
        }
    }

    override fun getItemCount(): Int {
        if(rideList == null)return 0
        else return rideList?.size!!
    }
    class rideViewAdapter(itemView: View):RecyclerView.ViewHolder(itemView){
        val id=itemView.findViewById<TextView>(R.id.id)
        val city=itemView.findViewById<TextView>(R.id.cityname)
        val state=itemView.findViewById<TextView>(R.id.statename)
        val distance=itemView.findViewById<TextView>(R.id.distance)
        val path=itemView.findViewById<TextView>(R.id.path)
        val image=itemView.findViewById<ImageView>(R.id.item_iv1)
        val time=itemView.findViewById<TextView>(R.id.time)
        val origin=itemView.findViewById<TextView>(R.id.origin)


    }
}