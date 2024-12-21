package com.kodeleku.project_gestor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.kodeleku.project_gestor.databinding.ActivityCreateLanguageBinding
import com.kodeleku.project_gestor.models.Language
import kotlinx.coroutines.launch

class CreateLanguageActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityCreateLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Insertar Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Botón para guardar el lenguaje
        binding.btnSaveLanguage.setOnClickListener {
            val languageName = binding.etLanguageName.text.toString()

            if (languageName.isNotBlank()) {
                saveLanguage(languageName)
            } else {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun saveLanguage(name: String) {
        lifecycleScope.launch {
            val language = Language(name = name)
            MyApplication.database.languageDao().insertLanguage(language)

            Toast.makeText(this@CreateLanguageActivity, "Lenguaje guardado", Toast.LENGTH_SHORT).show()
            finish() // Finaliza la actividad y regresa a la anterior
        }
    }
}