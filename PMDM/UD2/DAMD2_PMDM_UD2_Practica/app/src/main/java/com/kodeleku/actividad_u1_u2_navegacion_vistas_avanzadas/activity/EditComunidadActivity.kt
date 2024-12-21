package com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.databinding.ActivityEditComunidadBinding
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.provider.ProviderComunidades

class EditComunidadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditComunidadBinding
    private var comunidadId: Int = -1 // ID de la comunidad seleccionada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditComunidadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el ID de la comunidad a editar
        comunidadId = intent.getIntExtra("comunidadId", -1)
        val comunidad = ProviderComunidades.cargarLista().find { it.id == comunidadId }

        // Mostrar el nombre actual en el campo de texto
        comunidad?.let {
            binding.etComunidadNombre.setText(it.nombre)
        }

        // Guardar los cambios en el nombre
        binding.btnGuardar.setOnClickListener {
            val nuevoNombre = binding.etComunidadNombre.text.toString()
            if (nuevoNombre.isNotEmpty()) {
                val resultIntent = Intent().apply {
                    putExtra("comunidadId", comunidadId)
                    putExtra("nombreComunidad", nuevoNombre) // Enviar el nuevo nombre de vuelta
                }
                setResult(RESULT_OK, resultIntent) // Configura el resultado
                finish() // Cierra la actividad
            } else {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
