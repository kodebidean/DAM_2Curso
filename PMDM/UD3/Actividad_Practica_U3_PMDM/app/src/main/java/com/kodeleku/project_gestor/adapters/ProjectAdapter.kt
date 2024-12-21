package com.kodeleku.project_gestor.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodeleku.project_gestor.R
import com.kodeleku.project_gestor.models.Project

class ProjectAdapter(
    private val projects: MutableList<Project>, // Cambiado a MutableList
    private val languages: Map<Int, String>, // Mapa de id -> nombre del lenguaje
    private val onClick: (Project) -> Unit,
    private val onDelete: (Project) -> Unit // Nuevo callback para borrar proyectos
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.bind(project, languages)
        holder.itemView.setOnClickListener { onClick(project) }
        holder.deleteButton.setOnClickListener { onDelete(project) } // Manejar el clic en el botón de borrar
    }

    override fun getItemCount(): Int = projects.size

    /**
     * Método para actualizar un proyecto en la lista.
     */
    fun updateProject(updatedProject: Project) {
        val index = projects.indexOfFirst { it.id == updatedProject.id }
        if (index != -1) {
            projects[index] = updatedProject
            notifyItemChanged(index) // Notifica que un elemento específico ha cambiado
        }
    }

    /**
     * Método para borrar un proyecto de la lista.
     */
    fun removeProject(deletedProject: Project) {
        val index = projects.indexOfFirst { it.id == deletedProject.id }
        if (index != -1) {
            projects.removeAt(index)
            notifyItemRemoved(index) // Notifica que un elemento ha sido eliminado
        }
    }

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val projectName: TextView = itemView.findViewById(R.id.tv_project_name)
        private val projectLanguage: TextView = itemView.findViewById(R.id.tv_project_language)
        private val projectPriority: TextView = itemView.findViewById(R.id.tv_project_priority)
        val deleteButton: Button = itemView.findViewById(R.id.btn_delete_project) // Nuevo botón

        @SuppressLint("SetTextI18n")
        fun bind(project: Project, languages: Map<Int, String>) {
            val context = itemView.context
            val languageName = languages[project.languageId] ?: context.getString(R.string.unknown_language)
            projectName.text = project.name
            projectLanguage.text = context.getString(R.string.project_language, languageName)
            projectPriority.text = context.getString(R.string.project_priority, project.priority)
        }
    }
}