---

# 🚀 Gestor de Proyectos - Unidad 3

Este es un Gestor de Proyectos desarrollado como parte de la práctica de la Unidad 3. La aplicación permite gestionar proyectos, asignarles un lenguaje de programación, establecer prioridades y horas estimadas, todo mediante el uso de **Room** como base de datos local y **DataStore** para configuraciones.

---

## 📋 **Índice**
1. [Características](#características)
2. [Requisitos Previos](#requisitos-previos)
3. [Instalación](#instalación)
4. [Uso de la Aplicación](#uso-de-la-aplicación)
5. [Tecnologías Utilizadas](#tecnologías-utilizadas)
6. [Estructura del Proyecto](#estructura-del-proyecto)

---

## ✨ **Características**
- **Añadir Proyectos** con nombre, descripción, prioridad y horas estimadas.
- **Asignar Lenguajes de Programación** (e.g., Kotlin, Java) a los proyectos.
- **Visualizar Proyectos** en una lista con detalles clave.
- **Actualizar Proyectos** editando sus campos fácilmente.
- **Persistencia Local** mediante **Room** y gestión de preferencias con **DataStore**.

---

## ⚙️ **Requisitos Previos**
Antes de ejecutar la aplicación, asegúrate de tener instalados:
- [Android Studio](https://developer.android.com/studio)
- Un emulador Android o un dispositivo físico con API 26 o superior.
- Gradle configurado correctamente en tu entorno.

---

## 🛠️ **Instalación**
1. Clona el repositorio en tu máquina local:
   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   ```
2. Abre el proyecto en **Android Studio**.
3. Sincroniza las dependencias de Gradle.
4. Ejecuta la aplicación en un emulador o dispositivo físico.

---

## 📱 **Uso de la Aplicación**
1. Inicia la aplicación y añade un **lenguaje** desde el botón **"Añadir Lenguaje"**.
2. Crea un nuevo **proyecto** asignando un lenguaje, prioridad y horas.
3. Visualiza todos los proyectos creados en la lista principal.
4. Selecciona un proyecto para editar sus detalles o realizar ajustes.

---

## 🧰 **Tecnologías Utilizadas**
- **Kotlin**: Lenguaje principal de desarrollo.
- **Room**: Persistencia local de datos.
- **DataStore**: Almacenamiento de configuraciones.
- **ViewBinding**: Manejo de vistas de forma segura.
- **ConstraintLayout**: Diseño de interfaces responsive.
- **RecyclerView**: Lista dinámica de proyectos.

---

## 📁 **Estructura del Proyecto**
```
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/kodeleku/project_gestor/
│   │   │   │   ├── activities/
│   │   │   │   │   ├── CreateProjectActivity.kt
│   │   │   │   │   ├── CreateLanguageActivity.kt
│   │   │   │   │   ├── ProjectListActivity.kt
│   │   │   │   │   └── ProjectDetailActivity.kt
│   │   │   │   ├── adapters/
│   │   │   │   │   └── ProjectAdapter.kt
│   │   │   │   ├── database/
│   │   │   │   │   ├── AppDatabase.kt
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   ├── ProjectDao.kt
│   │   │   │   │   │   └── LanguageDao.kt
│   │   │   │   └── models/
│   │   │   │       ├── Project.kt
│   │   │   │       └── Language.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   └── drawable/
│   │   ├── AndroidManifest.xml
└── ...
```

---

## 📧 **Contacto**
Si necesitas más información o ayuda, puedes contactarme:
- **Nombre:** Imanol
- **Correo:** kodebidean@gmail.com
- **GitHub:** [kodebidean](https://github.com/kodebian)

---