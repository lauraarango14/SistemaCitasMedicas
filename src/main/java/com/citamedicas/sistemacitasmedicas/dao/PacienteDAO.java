package com.citamedicas.sistemacitasmedicas.dao;

import com.citamedicas.sistemacitasmedicas.conexion.ConexionBD;
import com.citamedicas.sistemacitasmedicas.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // INSERTAR
    public boolean insertar(Paciente paciente) {

        String sql = "INSERT INTO paciente "
                + "(nombre, apellido, documento, telefono, correo, fecha_nacimiento) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, paciente.getNombre());
            sentencia.setString(2, paciente.getApellido());
            sentencia.setString(3, paciente.getDocumento());
            sentencia.setString(4, paciente.getTelefono());
            sentencia.setString(5, paciente.getCorreo());
            sentencia.setDate(6,
                    java.sql.Date.valueOf(paciente.getFechaNacimiento()));

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar paciente: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR TODOS
    public List<Paciente> listar() {

        List<Paciente> pacientes = new ArrayList<>();

        String sql = "SELECT id_paciente, nombre, apellido, documento, "
                + "telefono, correo, fecha_nacimiento "
                + "FROM paciente ORDER BY id_paciente";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Paciente paciente = new Paciente();

                paciente.setIdPaciente(resultado.getInt("id_paciente"));
                paciente.setNombre(resultado.getString("nombre"));
                paciente.setApellido(resultado.getString("apellido"));
                paciente.setDocumento(resultado.getString("documento"));
                paciente.setTelefono(resultado.getString("telefono"));
                paciente.setCorreo(resultado.getString("correo"));

                if (resultado.getDate("fecha_nacimiento") != null) {
                    paciente.setFechaNacimiento(
                            resultado.getDate("fecha_nacimiento").toLocalDate()
                    );
                }

                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar pacientes: "
                    + e.getMessage());
        }

        return pacientes;
    }

    // ACTUALIZAR
    public boolean actualizar(Paciente paciente) {

        String sql = "UPDATE paciente SET "
                + "nombre = ?, "
                + "apellido = ?, "
                + "documento = ?, "
                + "telefono = ?, "
                + "correo = ?, "
                + "fecha_nacimiento = ? "
                + "WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, paciente.getNombre());
            sentencia.setString(2, paciente.getApellido());
            sentencia.setString(3, paciente.getDocumento());
            sentencia.setString(4, paciente.getTelefono());
            sentencia.setString(5, paciente.getCorreo());
            sentencia.setDate(6,
                    java.sql.Date.valueOf(paciente.getFechaNacimiento()));
            sentencia.setInt(7, paciente.getIdPaciente());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar paciente: "
                    + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int idPaciente) {

        String sql = "DELETE FROM paciente WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPaciente);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar paciente: "
                    + e.getMessage());
            return false;
        }
    }
}