package com.example.laboratorio6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class adaptadorUrbano(val imagenes:List<String>):RecyclerView.Adapter<holderUrbano>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderUrbano {
        val layoutInflater:LayoutInflater=LayoutInflater.from(parent.context)
        return holderUrbano(layoutInflater.inflate(R.layout.,parent,false))
    }

    override fun getItemCount(): Int =imagenes.size

    override fun onBindViewHolder(holder: holderUrbano, position: Int) {
        val item:String=imagenes[position]
        holder.bind(item)
    }

}
