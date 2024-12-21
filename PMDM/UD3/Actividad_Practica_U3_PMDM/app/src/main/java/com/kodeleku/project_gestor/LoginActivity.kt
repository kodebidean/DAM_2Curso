package com.kodeleku.project_gestor
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.kodeleku.project_gestor.databinding.ActivityLoginBinding

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Insertar Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Verificar si el usuario ya está logueado
        lifecycleScope.launch {
            val isLoggedIn = DataStoreManager.isLoggedIn(this@LoginActivity).first()
            if (isLoggedIn) {
                navigateToProjectList()
            }
        }

        // Manejo del botón de inicio de sesión
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch {
                val storedUsername = DataStoreManager.getUsername(this@LoginActivity).first()
                val storedPassword = DataStoreManager.getPassword(this@LoginActivity).first()

                if (username == storedUsername && password == storedPassword) {
                    DataStoreManager.setLoggedIn(this@LoginActivity, true)
                    navigateToProjectList()
                } else {
                    Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Botón para ir a la actividad de registro
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun navigateToProjectList() {
        val intent = Intent(this, ProjectListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
