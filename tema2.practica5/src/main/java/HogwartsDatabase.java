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

    // Método para obtener todas las asignaturas obligatorias
    public static List<String> listarAsignaturasObligatorias(Connection connection) {
        List<String> asignaturas = new ArrayList<>();
        String sql = "SELECT nombre_asignatura FROM Asignatura WHERE obligatoria = true";
        try (Statement consulta = connection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()) {
                asignaturas.add(result.getString("nombre_asignatura"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaturas;
    }

    // Método para obtener la mascota de un estudiante específico (Hermione Granger)
    public static String obtenerMascotaEstudiante(String nombre, String apellido, Connection conection) {
        String sql = "SELECT nombre_mascota FROM Mascota WHERE id_estudiante = (SELECT id_estudiante FROM Estudiante WHERE nombre = ? AND apellido = ?)";
        try (PreparedStatement consulta = conection.prepareStatement(sql)) {
            consulta.setString(1, nombre);
            consulta.setString(2, apellido);
            ResultSet result = consulta.executeQuery();
            if (result.next()) {
                return result.getString("nombre_mascota");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No tiene mascota.";
    }

    // Método para listar estudiantes sin mascota
    public static void listarEstudiantesSinMascota(Connection connection) {
        String sql = "SELECT nombre, apellido FROM Estudiante " +
                "WHERE id_estudiante NOT IN (SELECT id_estudiante FROM Mascota WHERE id_estudiante IS NOT NULL)";
        try (Statement consulta = connection.createStatement()) {
            ResultSet result = consulta.executeQuery(sql);
            if (!result.isBeforeFirst()) {
                System.out.println("No hay estudiantes sin mascota.");
            } else {
                while (result.next()) {
                    System.out.println(result.getString("nombre") + " " + result.getString("apellido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para calcular el promedio de calificaciones de un estudiante
    public static double calcularPromedioCalificaciones(String nombre, String apellido, Connection connection) {
        String sql = "SELECT AVG(calificacion) AS promedio FROM Estudiante_Asignatura " +
                "WHERE id_estudiante = (SELECT id_estudiante FROM Estudiante WHERE nombre = ? AND apellido = ?)";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setString(1, nombre);
            consulta.setString(2, apellido);
            ResultSet result = consulta.executeQuery();
            if (result.next()) {
                return result.getDouble("promedio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Método para consultar el número de estudiantes por casa
    public static void numeroEstudiantesPorCasa(Connection connection) {
        String sql = "SELECT nombre_casa, COUNT(*) AS numero_estudiantes FROM Estudiante " +
                "JOIN Casa ON Estudiante.id_casa = Casa.id_casa GROUP BY nombre_casa";
        try (Statement consulta = connection.createStatement()) {
            ResultSet resultado = consulta.executeQuery(sql);
            while (resultado.next()) {
                System.out.println("Casa: " + resultado.getString("nombre_casa") + " - Estudiantes: " + resultado.getInt("numero_estudiantes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar estudiantes matriculados en una asignatura específica
    public static void listarEstudiantesPorAsignatura(String nombreAsignatura, Connection connection) {
        String sql = "SELECT E.nombre, E.apellido FROM Estudiante_Asignatura EA " +
                "JOIN Estudiante E ON EA.id_estudiante = E.id_estudiante " +
                "JOIN Asignatura A ON EA.id_asignatura = A.id_asignatura " +
                "WHERE A.nombre_asignatura = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setString(1, nombreAsignatura);
            ResultSet result = consulta.executeQuery();
            while (result.next()) {
                System.out.println(result.getString("nombre") + " " + result.getString("apellido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo estudiante
    public static void insertarEstudiante(String nombre, String apellido, int idCasa, int añoCurso, Date fechaNacimiento, Connection connection) {
        String sql = "INSERT INTO Estudiante (nombre, apellido, id_casa, año_curso, fecha_nacimiento) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setString(1, nombre);
            consulta.setString(2, apellido);
            consulta.setInt(3, idCasa);
            consulta.setInt(4, añoCurso);
            consulta.setDate(5, fechaNacimiento);
            consulta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para modificar el aula de una asignatura
    public static void modificarAulaAsignatura(int idAsignatura, String nuevoAula, Connection connection) {
        String sql = "UPDATE Asignatura SET aula = ? WHERE id_asignatura = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setString(1, nuevoAula);
            consulta.setInt(2, idAsignatura);
            consulta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para desmatricular a un estudiante de una asignatura
    public static void desmatricularEstudianteDeAsignatura(int idEstudiante, int idAsignatura, Connection connection) {
        String sql = "DELETE FROM Estudiante_Asignatura WHERE id_estudiante = ? AND id_asignatura = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setInt(1, idEstudiante);
            consulta.setInt(2, idAsignatura);
            consulta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
