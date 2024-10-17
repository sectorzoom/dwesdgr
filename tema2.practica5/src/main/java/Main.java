
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String urlConexion = "jdbc:postgresql://hogwarts.cf0oge62knpk.us-east-1.rds.amazonaws.com/hogwarts";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456789";

    public static void main(String[] args) throws SQLException {
        // Establecer conexión única
        try (Connection connection = DriverManager.getConnection(urlConexion, USER, PASSWORD)) {
            // Consultar estudiantes de Gryffindor
            System.out.println("Estudiantes de Griffindor: ");
            HogwartsDatabase.consultarEstudiantesPorCasa("Gryffindor", connection);

            // Listar asignaturas obligatorias
            System.out.println("\nAsignaturas obligatorias: ");
            System.out.println(HogwartsDatabase.listarAsignaturasObligatorias(connection));

            // Consultar la mascota de Hermione Granger
            System.out.println("\nMascota de Hermione: ");
            System.out.println(HogwartsDatabase.obtenerMascotaEstudiante("Hermione", "Granger", connection));

            // Listar estudiantes sin mascota
            System.out.println("\nEstudiantes sin mascota: ");
            HogwartsDatabase.listarEstudiantesSinMascota(connection);

            // Calcular promedio de calificaciones de Harry Potter
            System.out.println("\nPromedio notas Harry Potter: ");
            System.out.println(HogwartsDatabase.calcularPromedioCalificaciones("Harry", "Potter", connection));

            // Consultar número de estudiantes por casa
            System.out.println("\nNúmero de estudiantes en cada casa: ");
            HogwartsDatabase.numeroEstudiantesPorCasa(connection);

            // Listar estudiantes matriculados en "Defensa Contra las Artes Oscuras"
            System.out.println("\nEstudiantes matriculados en Defensa Contra las Artes Oscuras: ");
            HogwartsDatabase.listarEstudiantesPorAsignatura("Defensa Contra las Artes Oscuras", connection);

            /*
            // Insertar un nuevo estudiante
            Date fechaNacimiento = Date.valueOf("2005-05-02"); // Fecha de nacimiento en formato YYYY-MM-DD
            HogwartsDatabase.insertarEstudiante("Luna", "Lovegood", 3, 5, fechaNacimiento, connection);  // Ejemplo de inserción

            // Modificar el aula de una asignatura
            HogwartsDatabase.modificarAulaAsignatura(1, "Aula 204", connection);  // Modificar aula de la asignatura con id 1

            // Desmatricular a un estudiante de una asignatura
            HogwartsDatabase.desmatricularEstudianteDeAsignatura(10, 2, connection);  // Desmatricular estudiante con id 10 de la asignatura con id 2

             */

        }

    }
}

