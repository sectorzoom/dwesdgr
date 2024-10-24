package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HogwartsDatabase {

    // Método para obtener estudiantes de la casa Gryffindor
    public static void consultarEstudiantesPorCasa(String casa, Connection conection) {
        String sql = "SELECT nombre, apellido FROM Estudiante WHERE id_casa = (SELECT id_casa FROM Casa WHERE nombre_casa = ?)";
        try (PreparedStatement consulta = conection.prepareStatement(sql)) {
            consulta.setString(1, casa);
            ResultSet result = consulta.executeQuery();
            while (result.next()) {
                System.out.println(result.getString("nombre") + " " + result.getString("apellido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //1. Consulta de estudiantes y sus casas
    //Haz una consulta que devuelva el nombre completo de los estudiantes y de la casa.
    public static void consultarEstudianteYCasa(Connection conection) {
        String sql = "SELECT nombre, apellido, nombre_casa FROM Estudiante JOIN Casa ON Estudiante.id_casa = Casa.id_casa";
        try (Statement consulta = conection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString("nombre") + " " + result.getString("apellido") + " - " + result.getString("nombre_casa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //2. Consulta de estudiantes y sus mascotas
    //Haz una consulta que muestre a todos los estudiantes y el nombre de la mascota (en caso de que la tengan).
    public static void consultarEstudianteYMascota(Connection conection) {
        String sql = "SELECT nombre, apellido, nombre_mascota FROM Estudiante LEFT JOIN Mascota ON Estudiante.id_estudiante = Mascota.id_estudiante";
        try (Statement consulta = conection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString("nombre") + " " + result.getString("apellido") + " - " + result.getString("nombre_mascota"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3. Consulta de estudiantes y sus mascotas
    //Haz una consulta que muestre el nombre del estudiante y el nombre de su mascota. Sólo los estudiantes que tienen mascotas.
    public static void consultarEstudianteYMascota2(Connection conection) {
        String sql = "SELECT nombre, apellido, nombre_mascota FROM Estudiante LEFT JOIN Mascota ON Estudiante.id_estudiante = Mascota.id_estudiante WHERE nombre_mascota IS NOT NULL";
        try (Statement consulta = conection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()) {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //4. Consulta de estudiantes sin mascotas
    //Muestra el nombre de los estudiantes sin mascotas
    public static void consultarEstudiantesSinMascota(Connection conection) {
        String sql = "SELECT nombre, apellido FROM Estudiante WHERE id_estudiante NOT IN (SELECT id_estudiante FROM Mascota WHERE id_estudiante IS NOT NULL)";
        try (Statement consulta = conection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString("nombre") + " " + result.getString("apellido"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //5. Notas promedio por estudiante
    //Haz una consulta que calcule la calificación promedio de cada estudiante sobre todas las asignaturas
    public static void consultarNotasPromedioEstudiante(Connection conection) {
        String sql = "SELECT nombre, apellido, AVG(calificacion) AS promedio FROM Estudiante JOIN Asignatura ON Estudiante.id_estudiante = Asignatura.id_estudiante GROUP BY nombre, apellido";
        try (Statement consulta = conection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString("nombre") + " " + result.getString("apellido") + " - " + result.getString("promedio"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //6. Número de estudiantes en quinto año
    //Haz una consulta que devuelve el nombre de cada casa y el número de estudiantes que están en quinto año..
    public static void consultarEstudiantesEnQuintoAnio(Connection conection) {
        String sql = "SELECT nombre_casa, COUNT(*) AS numero_estudiantes FROM Estudiante JOIN Casa ON Estudiante.id_casa = Casa.id_casa WHERE anio = 5 GROUP BY nombre_casa";
        try (Statement consulta = conection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString("nombre_casa") + " - " + result.getString("numero_estudiantes"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //7. Consulta de las mejores calificaciones en una asignatura específica
    //Muestra el estudiante con mayor calificación en una asignatura concreta. La asignatura la debes buscar por nombre (no vale por id).
    public static void consultarMejorCalificacion(Connection conection) {

    }

    //8. Promedio de calificaciones en una asignatura para una casa
    //Calcula el promedio de calificaciones por casa para una asignatura en concreto.
    public static void promedioCalificacionesPorCasaDeAsignatura(Connection conection) {

    }

    //9. Actualiza las calificaciones
    //Incrementa las calificaciones un 10% del alumnado que está en su último año.
    public static void actualizarCalificaciones(Connection conection) {

    }

    //10. Desmatricular estudiantes
    //Eliminar asignaturas optativas de los estudiantes que tienen calificaciones inferiores a 5 en dichas asignaturas.
    public static void desmatricularEstudiantes(Connection conection) {

    }



}
