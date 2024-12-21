
# Práctica Unidad 2 - Programación Multimedia y Dispositivos Móviles (PMDM) - DAM

## Descripción de la Actividad

Esta es la práctica de la Unidad 2 del módulo de Programación Multimedia y Dispositivos Móviles, en la cual se desarrolló una aplicación en Android para la gestión de comunidades autónomas. La aplicación permite al usuario ver una lista de comunidades, eliminar y editar nombres de las comunidades mediante un menú contextual, y recargar o limpiar la lista desde un menú general en la barra de herramientas.

## Funcionalidades

- **Visualización de Comunidades Autónomas**: La aplicación presenta una lista de comunidades autónomas, mostrando el nombre y la bandera de cada una.
- **Menú Contextual**: Al mantener presionado sobre una comunidad, aparece un menú contextual con las opciones:
    - **Editar**: Permite modificar el nombre de la comunidad en una nueva actividad.
    - **Eliminar**: Elimina la comunidad seleccionada con confirmación mediante un cuadro de diálogo.
- **Menú General**: Ubicado en la Toolbar, incluye las opciones:
    - **Recargar**: Restaura la lista original de comunidades.
    - **Limpiar**: Elimina todas las comunidades de la lista.
- **Notificación en Click**: Al hacer click en una comunidad, muestra un mensaje `Toast` indicando el nombre de la comunidad seleccionada.

## Estructura del Proyecto

- **MainActivity**: Actividad principal que muestra la lista de comunidades y gestiona el menú general.
- **EditComunidadActivity**: Actividad secundaria para la edición del nombre de una comunidad.
- **ProviderComunidades**: Proveedor de datos, que inicializa y devuelve una lista de comunidades con sus respectivos nombres y banderas.
- **ComunidadAdapter y ComunidadViewHolder**: Adapter y ViewHolder para gestionar la lista de comunidades en el `RecyclerView`.
- **Layouts**:
    - `activity_main.xml`: Layout principal que contiene el `RecyclerView` y la `Toolbar`.
    - `item_comunidad.xml`: Layout para los elementos individuales de la lista de comunidades.
    - `activity_edit_comunidad.xml`: Layout para la actividad de edición de nombre de comunidad.

## Tecnologías y Herramientas

- **Android Studio**
- **Kotlin**
- **RecyclerView y Adapter**: Para la gestión de la lista.
- **AlertDialog**: Para confirmar la eliminación de comunidades.
- **Menus**: Para el menú contextual y el menú general.
- **Picasso**: Biblioteca para cargar imágenes en los elementos de la lista.
- **View Binding**: Para una gestión más segura y eficiente de las vistas.

## Instalación y Configuración

1. Clona el repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```
2. Abre el proyecto en Android Studio.
3. Sincroniza las dependencias en `build.gradle`.
4. Ejecuta la aplicación en un emulador o dispositivo Android.

## Notas

Esta práctica es parte del contenido curricular del segundo curso de Desarrollo de Aplicaciones Multiplataforma (DAM). Está diseñada para poner en práctica conceptos como la gestión de listas con `RecyclerView`, menús contextuales, actividades, y comunicación entre actividades usando `Intent` y `startActivityForResult`.

---