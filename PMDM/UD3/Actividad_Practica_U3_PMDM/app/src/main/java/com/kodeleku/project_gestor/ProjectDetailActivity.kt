package com.kodeleku.project_gestor

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kodeleku.project_gestor.databinding.ActivityProjectDetailBinding
import com.kodeleku.project_gestor.models.Project
import kotlinx.coroutines.launch

class ProjectDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityProjectDetailBinding
    private var projectId: Int = 0
    private var currentProject: Project? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Obtener el ID del proyecto de los extras
        projectId = intent.getIntExtra("PROJECT_ID", 0)

        if (projectId != 0) {
            loadProjectDetails()
        } else {
            Toast.makeText(this, "Error al cargar el proyecto", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Configurar el botón de guardar
        binding.btnUpdateProject.setOnClickListener {
            updateProjectDetails()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadProjectDetails() {
        lifecycleScope.launch {
            val dao = MyApplication.database.projectDao()
            val languageDao = MyApplication.database.languageDao()

            // Obtener el proyecto actual
            currentProject = dao.getProjectById(projectId)

            if (currentProject != null) {
                // Obtener el nombre del lenguaje
                val language = languageDao.getLanguageById(currentProject!!.languageId)

                // Mostrar los detalles en los campos correspondientes
                binding.etProjectName.setText(currentProject!!.name) // Editable
                binding.tvProjectLanguage.text = "Lenguaje: ${language?.name ?: "Desconocido"}"
                binding.etProjectDescription.setText(currentProject!!.description)
                binding.etProjectHours.setText(currentProject!!.hours.toString())
                binding.etProjectPriority.setText(currentProject!!.priority.toString())
            } else {
                Toast.makeText(this@ProjectDetailActivity, "Proyecto no encontrado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun updateProjectDetails() {
        // Validar los inputs
        val newName = binding.etProjectName.text.toString()
        val newDescription = binding.etProjectDescription.text.toString()
        val newHours = binding.etProjectHours.text.toString().toIntOrNull()
        val newPriority = binding.etProjectPriority.text.toString().toIntOrNull()

        if (newName.isNotBlank() && newDescription.isNotBlank() && newHours != null && newPriority != null) {
            lifecycleScope.launch {
                currentProject?.let { project ->
                    val updatedProject = project.copy(
                        name = newName,
                        description = newDescription,
                        hours = newHours,
                        priority = newPriority
                    )

                    // Actualizar en la base de datos
                    MyApplication.database.projectDao().updateProject(updatedProject)

                    Toast.makeText(this@ProjectDetailActivity, "Proyecto actualizado", Toast.LENGTH_SHORT).show()

                    // Devuelve un resultado a ProjectListActivity
                    setResult(RESULT_OK) // Indicar que el proyecto fue actualizado
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}
