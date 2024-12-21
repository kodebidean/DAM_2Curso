package com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.provider

import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.R
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.model.Comunidad


class ProviderComunidades {
    companion object {
        fun cargarLista(): MutableList<Comunidad> {
            return mutableListOf(
                Comunidad(1, "Andalucía", R.drawable.andalucia),
                Comunidad(2, "Aragón", R.drawable.aragon),
                Comunidad(3, "Asturias", R.drawable.asturias),
                Comunidad(4, "Islas Baleares", R.drawable.baleares),
                Comunidad(5, "Canarias", R.drawable.canarias),
                Comunidad(6, "Cantabria", R.drawable.cantabria),
                Comunidad(7, "Castilla y León", R.drawable.castillaleon),
                Comunidad(8, "Castilla-La Mancha", R.drawable.castillamancha),
                Comunidad(9, "Cataluña", R.drawable.catalunya),
                Comunidad(10, "Ceuta", R.drawable.ceuta),
                Comunidad(11, "Extremadura", R.drawable.extremadura),
                Comunidad(12, "Galicia", R.drawable.galicia),
                Comunidad(13, "La Rioja", R.drawable.larioja),
                Comunidad(14, "Madrid", R.drawable.madrid),
                Comunidad(15, "Melilla", R.drawable.melilla),
                Comunidad(16, "Murcia", R.drawable.murcia),
                Comunidad(17, "Navarra", R.drawable.navarra),
                Comunidad(18, "País Vasco", R.drawable.paisvasco),
                Comunidad(19, "Comunidad Valenciana", R.drawable.valencia)
            )
        }
    }
}
