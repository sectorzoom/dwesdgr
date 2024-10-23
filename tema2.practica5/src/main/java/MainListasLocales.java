import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainListasLocales {
    public static void main(String[] args) {
        try {
            HogwartsDatabaseConListasLocales hogwartsDb = getHogwartsDatabase2();

            // Ejercicio 1: Mostrar estudiantes de Gryffindor
            System.out.println("\nEstudiantes de Gryffindor:");
            hogwartsDb.mostrarEstudiantesPorCasa("Gryffindor");
            // Listar asignaturas obligatorias
            System.out.println("\nAsignaturas obligatorias: ");
            hogwartsDb.obtenerAsignaturasObligatorias();
            // Consultar la mascota de Hermione Granger
            System.out.println("\nMascota de Hermione: ");
            hogwartsDb.mostrarMascotaDeEstudiante("Hermione", "Granger");
            // Listar estudiantes sin mascota
            System.out.println("\nEstudiantes sin mascota: ");
            hogwartsDb.mostrarEstudiantesSinMascota();
            // Calcular promedio de calificaciones de Harry Potter
            System.out.println("\nPromedio notas Harry Potter: ");
            hogwartsDb.mostrarPromedioCalificaciones("Harry", "Potter");
            // Consultar número de estudiantes por casa
            System.out.println("\nNúmero de estudiantes en cada casa: ");
            hogwartsDb.contarEstudiantesPorCasa();
            // Listar estudiantes matriculados en "Defensa Contra las Artes Oscuras"
            System.out.println("\nEstudiantes matriculados en Defensa Contra las Artes Oscuras: ");
            hogwartsDb.listarEstudiantesPorAsignatura("Defensa Contra las Artes Oscuras");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static HogwartsDatabaseConListasLocales getHogwartsDatabase2() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        List<Casa> casas = new ArrayList<>();
        List<Mascota> mascotas = new ArrayList<>();
        List<Asignatura> asignaturas = new ArrayList<>();
        List<EstudianteAsignatura> estudianteAsignaturas = new ArrayList<>();
        return new HogwartsDatabaseConListasLocales("jdbc:postgresql://hogwarts.cf0oge62knpk.us-east-1.rds.amazonaws.com/hogwarts", "postgres", "123456789", estudiantes, casas, mascotas,asignaturas, estudianteAsignaturas);
    }
}
