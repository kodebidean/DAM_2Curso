package com.kodeleku.project_gestor

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                logout()
                true
            }
            android.R.id.home -> { // Maneja el botón de volver atrás
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun logout() {
        lifecycleScope.launch {
            // Establece el valor de loggedIn como false en DataStore
            DataStoreManager.setLoggedIn(this@BaseActivity, false)
            // Navega a la pantalla de inicio de sesión
            val intent = Intent(this@BaseActivity, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity() // Cierra todas las actividades
        }
    }
}
