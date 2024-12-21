package com.kodeleku.project_gestor

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.kodeleku.project_gestor.databinding.ActivityCreateProjectBinding
import com.kodeleku.project_gestor.models.Project
import kotlinx.coroutines.launch
import java.util.*

class CreateProjectActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateProjectBinding
    private var selectedLanguageId: Int? = null
    private var selectedDate: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityCreateProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadLanguages()

        // Insertar Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Configurar el botón de fecha
        binding.btnStartDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Configurar el botón de guardar
        binding.saveProjectButton.setOnClickListener {
            val projectName = binding.etProjectName.text.toString()
            val projectDescription = binding.etProjectDescription.text.toString()
            val projectPriority = binding.etProjectPriority.text.toString().toIntOrNull()
            val projectHours = binding.etProjectHours.text.toString().toIntOrNull()

            if (projectName.isNotBlank() && projectPriority != null && projectHours != null && selectedLanguageId != null && selectedDate != null) {
                saveProject(projectName, projectDescription, projectPriority, projectHours)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun loadLanguages() {
        lifecycleScope.launch {
            val languages = MyApplication.database.languageDao().getAllLanguages()
            if (languages.isNotEmpty()) {
                val adapter = ArrayAdapter(
                    this@CreateProjectActivity,
                    android.R.layout.simple_spinner_item,
                    languages.map { it.name }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spnLanguage.adapter = adapter

                // Al seleccionar un lenguaje
                binding.spnLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedLanguageId = languages[position].id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        selectedLanguageId = null
                    }
                }
            } else {
                Toast.makeText(this@CreateProjectActivity, "No hay lenguajes disponibles", Toast.LENGTH_SHORT).show()
                finish() // Finaliza la actividad
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, y, m, d ->
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.set(y, m, d)
            selectedDate = selectedCalendar.timeInMillis

            binding.btnStartDate.text = getString(R.string.date_format, d, m + 1, y)
        }, year, month, day).show()
    }

    private fun saveProject(name: String, description: String, priority: Int, hours: Int) {
        lifecycleScope.launch {
            val project = Project(
                name = name,
                description = description,
                priority = priority,
                hours = hours,
                startDate = selectedDate!!,
                languageId = selectedLanguageId!!
            )
            MyApplication.database.projectDao().insertProject(project)
            Toast.makeText(this@CreateProjectActivity, "Proyecto guardado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
