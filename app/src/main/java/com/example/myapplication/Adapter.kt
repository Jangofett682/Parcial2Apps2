package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val quakes: List<Terremoto>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tvMag: TextView = view.findViewById(R.id.textViewMag)

        fun bind(feature: Terremoto) {
            tvMag.text = feature.properties.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.listitem, parent, false))
    }

    override fun getItemCount(): Int {
        return quakes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quake = quakes[position]
        holder.bind(quake)
    }
}