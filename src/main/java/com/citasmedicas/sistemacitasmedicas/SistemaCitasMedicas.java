package com.citamedicas.sistemacitasmedicas;

import com.citamedicas.sistemacitasmedicas.dao.PacienteDAO;
import com.citamedicas.sistemacitasmedicas.modelo.Paciente;

import java.time.LocalDate;
import java.util.List;

public class SistemaCitasMedicas {

    public static void main(String[] args) {
        String password = System.getenv("DB_PASSWORD");

        PacienteDAO pacienteDAO = new PacienteDAO();

        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTION DE CITAS MEDICAS");
        System.out.println("==============================================");

        // 1. INSERTAR
        Paciente paciente = new Paciente(
                "Carlos",
                "Rodriguez",
                "100000001",
                "3001234567",
                "carlos.rodriguez@gmail.com",
                LocalDate.of(1995, 5, 15)
        );

        boolean insertado = pacienteDAO.insertar(paciente);

        if (insertado) {
            System.out.println("1. INSERTAR: Paciente registrado correctamente.");
        } else {
            System.out.println("1. INSERTAR: No se pudo registrar el paciente.");
        }

        // 2. CONSULTAR
        System.out.println();
        System.out.println("2. CONSULTAR: Lista de pacientes");

        List<Paciente> pacientes = pacienteDAO.listar();

        for (Paciente p : pacientes) {
            System.out.println(p);
        }

        // 3. ACTUALIZAR
        System.out.println();
        System.out.println("3. ACTUALIZAR: Modificando paciente...");

        if (!pacientes.isEmpty()) {

            Paciente pacienteActualizar = pacientes.get(pacientes.size() - 1);

            pacienteActualizar.setTelefono("3119876543");
            pacienteActualizar.setCorreo("carlos.actualizado@gmail.com");

            boolean actualizado = pacienteDAO.actualizar(pacienteActualizar);

            if (actualizado) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el paciente.");
            }

            // 4. ELIMINAR
            System.out.println();
            System.out.println("4. ELIMINAR: Eliminando paciente...");

            boolean eliminado =
                    pacienteDAO.eliminar(pacienteActualizar.getIdPaciente());

            if (eliminado) {
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el paciente.");
            }

        } else {
            System.out.println("No hay pacientes para actualizar o eliminar.");
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("          PRUEBA CRUD FINALIZADA");
        System.out.println("==============================================");
    }
}