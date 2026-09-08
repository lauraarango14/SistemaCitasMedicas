# Sistema Web de Gestión de Citas Médicas

## Descripción

Sistema desarrollado para gestionar la información relacionada con las citas médicas de una institución de salud.

El proyecto permite administrar pacientes y realizar operaciones básicas de registro, consulta, actualización y eliminación de información mediante conexión a una base de datos MySQL.

## Módulo desarrollado

### Módulo de Pacientes

El módulo permite realizar las siguientes operaciones CRUD:

- Registrar pacientes.
- Consultar pacientes registrados.
- Actualizar información de pacientes.
- Eliminar pacientes.

## Tecnologías utilizadas

- Java
- JDBC
- MySQL
- Maven
- Apache NetBeans IDE
- Git
- GitHub

## Base de datos

El sistema utiliza una base de datos MySQL denominada:

`proyecto`

La tabla utilizada para el módulo desarrollado es:

`paciente`

La tabla contiene información como:

- Identificador del paciente.
- Nombre.
- Apellido.
- Documento.
- Teléfono.
- Correo electrónico.
- Fecha de nacimiento.

## Arquitectura del módulo

El proyecto utiliza una separación básica por responsabilidades:

```text
com.citamedicas.sistemacitasmedicas
│
├── conexion
│   └── ConexionBD.java
│
├── dao
│   └── PacienteDAO.java
│
├── modelo
│   └── Paciente.java
│
└── SistemaCitasMedicas.java
