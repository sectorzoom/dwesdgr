package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainSQLRemoto {

    private static final String urlConexion = "jdbc:postgresql://hogwarts.cf0oge62knpk.us-east-1.rds.amazonaws.com/hogwarts";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456789";

    public static void main(String[] args) throws SQLException {
        // Establecer conexión única
        try (Connection connection = DriverManager.getConnection(urlConexion, USER, PASSWORD)) {
            /*
            System.out.println("\nEstudiantes y su casa");
            HogwartsDatabase.consultarEstudianteYCasa(connection);
            System.out.println("\nEstudiantes y su mascota");
            HogwartsDatabase.consultarEstudianteYMascota(connection);
            System.out.println("\nEstudiantes y su mascota 2");
            HogwartsDatabase.consultarEstudianteYMascota2(connection);
            System.out.println("\nEstudiantes sin mascota");
            HogwartsDatabase.consultarEstudiantesSinMascota(connection);
            System.out.println("\nConsultar nota promedio");
            HogwartsDatabase.consultarNotasPromedioEstudiante(connection);
            System.out.println("\nEstudiantes en quinto año");
            HogwartsDatabase.consultarEstudiantesEnQuintoAnio(connection);
            System.out.println("\nMejor calificación");
            HogwartsDatabase.consultarMejorCalificacion(connection, "Transformaciones");
            System.out.println("Promedio de calificaciones por casa en una asignatura");
            HogwartsDatabase.promedioCalificacionesPorCasaDeAsignatura(connection, "Pociones");
             */
            System.out.println("Incrementar las notas un 10%");
            HogwartsDatabase.actualizarCalificaciones(connection);




        }
    }
}

