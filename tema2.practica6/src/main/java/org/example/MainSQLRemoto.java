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
            // Consultar estudiantes de Gryffindor
            System.out.println("Estudiantes de Griffindor: ");
            HogwartsDatabase.consultarEstudiantesPorCasa("Gryffindor", connection);


        }
    }
}

