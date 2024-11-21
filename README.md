Gestión de Usuarios

Descripción
Esta aplicación permite la gestión de usuarios a través de un sistema de login, registro y administración. Fue desarrollada en Java utilizando Swing para la interfaz gráfica, JDBC para la conexión a la base de datos, y MySQL como sistema de almacenamiento.

Características
Login de Usuario:
Los usuarios pueden iniciar sesión ingresando su nombre de usuario y contraseña.
Las contraseñas están ocultas mientras se escriben.
Validación de campos obligatorios.

Registro de Usuarios:
Los nuevos usuarios pueden registrarse llenando un formulario.

Validaciones:
Todos los campos son obligatorios.
La contraseña y la confirmación deben coincidir.

Pantalla Principal:
Listado de todos los usuarios registrados.

Opciones para:
Actualizar datos: Modificar información del usuario seleccionado.
Eliminar usuario: Eliminar un usuario seleccionado.
Botón para cerrar sesión y regresar al login.

Requisitos software:
JDK 8 o superior.
MySQL.
IDE para Java (Eclipse).

Dependencias:
Conector JDBC para MySQL: mysql-connector-java.


Actualiza los datos de conexión en ConexionBD.java:

private static final String URL = "jdbc:mysql://localhost:3306/mi_base_datos";
private static final String USUARIO = "root";
private static final String CONTRASENA = "tu_contraseña";
