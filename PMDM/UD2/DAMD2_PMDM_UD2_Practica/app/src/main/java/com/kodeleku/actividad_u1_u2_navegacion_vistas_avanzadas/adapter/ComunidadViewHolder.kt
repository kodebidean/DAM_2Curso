package com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.databinding.ItemComunidadBinding
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.model.Comunidad
import com.squareup.picasso.Picasso

// Recibe una Vista como parámetro que hereda de ViewHolder
class ComunidadViewHolder (view: View): ViewHolder(view) {
    // Asociar ItemComunidadBinding a "view" que hereda de ViewHolder
    val binding = ItemComunidadBinding.bind(view)
    // Variable para la comunidad que va a representar
    lateinit var comunidad: Comunidad

    // Función para pintar los datos de nuestro item (Comunidad)
    fun render(item:Comunidad){
        binding.tvComunidad.text = item.nombre
        // Cargamos la imagen desde picasso asignando especificaciones
        Picasso.get().load(item.bandera).resize(70,100).centerInside().into(binding.ivComunidad)
    }

}