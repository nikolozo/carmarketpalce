package com.example.carmarketpalce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso

class CarsRecyclerAdapter(
    private var carsList: List<Cars>,
    private val onItemClick: (Cars) -> Unit
) : RecyclerView.Adapter<CarsRecyclerAdapter.CarsViewHolder>() {

    inner class CarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImage: ImageView = itemView.findViewById(R.id.imageView)
        val nameText: TextView = itemView.findViewById(R.id.textView)
        val modelText: TextView = itemView.findViewById(R.id.textView2)
        val yearText: TextView = itemView.findViewById(R.id.textView4)
        val priceText: TextView = itemView.findViewById(R.id.textView5)
        val engineText: TextView = itemView.findViewById(R.id.textView6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cars, parent, false)
        return CarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {

        val car = carsList[position]

        holder.nameText.text = car.name ?: "Unknown"
        holder.modelText.text = "Model: ${car.model ?: "N/A"}"
        holder.yearText.text = "Year: ${car.realiseData ?: 0}"
        holder.priceText.text = "Price: $${car.price ?: 0}"
        holder.engineText.text = "Engine: ${car.engine ?: 0.0}L"

        Picasso.get()
            .load(car.imageURL)
            .into(holder.carImage)

        holder.itemView.setOnClickListener {
            onItemClick(car)
        }

    }

    override fun getItemCount(): Int = carsList.size

    fun updateList(newList: List<Cars>) {
        carsList = newList
        notifyDataSetChanged()
    }
}
