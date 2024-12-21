package com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.R
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.model.Comunidad

class ComunidadAdapter(
    private val lista: List<Comunidad>,
    private val onItemClick: (Comunidad) -> Unit,
    private val onItemLongClick: (Comunidad) -> Unit
) : RecyclerView.Adapter<ComunidadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComunidadViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComunidadViewHolder(layoutInflater.inflate(R.layout.item_comunidad, parent, false))
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ComunidadViewHolder, position: Int) {
        val item = lista[position]
        holder.render(item)

        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        // Set long click listener
        holder.itemView.setOnLongClickListener {
            onItemLongClick(item)
            true
        }
    }
}
