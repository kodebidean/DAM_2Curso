package com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.R
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.adapter.ComunidadAdapter
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.databinding.ActivityMainBinding
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.model.Comunidad
import com.kodeleku.actividad_u1_u2_navegacion_vistas_avanzadas.provider.ProviderComunidades

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var comunidadesList: MutableList<Comunidad>
    private lateinit var adapter: ComunidadAdapter
    private var selectedComunidad: Comunidad? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        // Edge-to-Edge configuration
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        comunidadesList = ProviderComunidades.cargarLista()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ComunidadAdapter(
            comunidadesList,
            onItemClick = { comunidad -> showToast(comunidad) },
            onItemLongClick = { comunidad -> showContextMenu(comunidad) }
        )
        binding.rvAutonomas.layoutManager = LinearLayoutManager(this)
        binding.rvAutonomas.adapter = adapter
    }

    private fun showToast(comunidad: Comunidad) {
        Toast.makeText(this, "Yo soy de ${comunidad.nombre}", Toast.LENGTH_SHORT).show()
    }

    private fun showContextMenu(comunidad: Comunidad) {
        selectedComunidad = comunidad
        registerForContextMenu(binding.rvAutonomas)
        openContextMenu(binding.rvAutonomas)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_general, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_recargar -> {
                recargarLista()
                true
            }
            R.id.menu_limpiar -> {
                limpiarLista()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun recargarLista() {
        comunidadesList.clear()
        comunidadesList.addAll(ProviderComunidades.cargarLista())
        adapter.notifyDataSetChanged()
    }

    private fun limpiarLista() {
        comunidadesList.clear()
        adapter.notifyDataSetChanged()
    }

    // Menú contextual
    override fun onCreateContextMenu(menu: ContextMenu, v: android.view.View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_editar -> {
                selectedComunidad?.let { editarComunidad(it) }
                true
            }
            R.id.menu_eliminar -> {
                selectedComunidad?.let { eliminarComunidad(it) }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun editarComunidad(comunidad: Comunidad) {
        val intent = Intent(this, EditComunidadActivity::class.java).apply {
            putExtra("comunidadId", comunidad.id)
            putExtra("nombreComunidad", comunidad.nombre) // Enviar el nombre actual
        }
        startActivityForResult(intent, REQUEST_CODE_EDIT) // Usa startActivityForResult
    }

    // Sobrescribir onActivityResult para recibir el resultado de la edición
    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            val comunidadId = data?.getIntExtra("comunidadId", -1) ?: -1
            val nuevoNombre = data?.getStringExtra("nombreComunidad")

            // Actualizar el nombre de la comunidad en la lista
            val comunidad = comunidadesList.find { it.id == comunidadId }
            if (comunidad != null && nuevoNombre != null) {
                comunidad.nombre = nuevoNombre
                adapter.notifyDataSetChanged() // Notificar al adaptador para que refresque la vista
            }
        }
    }

    companion object {
        const val REQUEST_CODE_EDIT = 1001
    }


    private fun eliminarComunidad(comunidad: Comunidad) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Comunidad")
            .setMessage("¿Estás seguro de eliminar ${comunidad.nombre}?")
            .setPositiveButton("Sí") { _, _ ->
                comunidadesList.remove(comunidad)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "${comunidad.nombre} eliminada", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }
}

