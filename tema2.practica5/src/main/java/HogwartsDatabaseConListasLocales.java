import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class HogwartsDatabaseConListasLocales {
    private Connection connection;

    // Listas para almacenar los datos de las tablas
    private List<Estudiante> estudiantes;
    private List<Casa> casas;
    private List<Mascota> mascotas;
    private List<Asignatura> asignaturas;
    private List<EstudianteAsignatura> estudianteAsignaturas;


    public HogwartsDatabaseConListasLocales(String url, String username, String password, List<Estudiante> estudiantes, List<Casa> casas, List<Mascota> mascotas, List<Asignatura> asignaturas, List<EstudianteAsignatura> estudianteAsignaturas) throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
        this.estudiantes = estudiantes;
        this.casas = casas;
        this.mascotas = mascotas;
        this.asignaturas = asignaturas;
        this.estudianteAsignaturas = estudianteAsignaturas;
        
        cargarDatos();
    }

    // Método para cargar datos de la base de datos en las listas
    private void cargarDatos() throws SQLException {
        cargarEstudiantes();
        cargarCasas();
        cargarMascotas();
        cargarAsignaturas();
        cargarEstudianteAsignaturas();
    }

    private void cargarEstudiantes() throws SQLException {
        String query = "SELECT * FROM Estudiante";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                estudiantes.add(new Estudiante(
                        rs.getInt("id_estudiante"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("id_casa"),
                        rs.getInt("aÃ±o_curso"),
                        rs.getDate("fecha_nacimiento")
                ));
            }
        }
        for (int i = 0; i<estudiantes.size(); i++) {
            System.out.println(estudiantes.get(i).getNombre());
        }
    }

    private void cargarCasas() throws SQLException {
        String query = "SELECT * FROM Casa";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                casas.add(new Casa(
                        rs.getInt("id_casa"),
                        rs.getString("nombre_casa"),
                        rs.getString("fundador"),
                        rs.getString("jefe_casa"),
                        rs.getString("fantasma")
                ));
            }
        }
    }

    private void cargarMascotas() throws SQLException {
        String query = "SELECT * FROM Mascota";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                mascotas.add(new Mascota(
                        rs.getInt("id_mascota"),
                        rs.getString("nombre_mascota"),
                        rs.getString("especie"),
                        rs.getInt("id_estudiante")
                ));
            }
        }
    }

    private void cargarAsignaturas() throws SQLException {
        String query = "SELECT * FROM Asignatura";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                asignaturas.add(new Asignatura(
                        rs.getInt("id_asignatura"),
                        rs.getString("nombre_asignatura"),
                        rs.getString("aula"),
                        rs.getBoolean("obligatoria")
                ));
            }
        }
    }

    private void cargarEstudianteAsignaturas() throws SQLException {
        String query = "SELECT * FROM Estudiante_Asignatura";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                estudianteAsignaturas.add(new EstudianteAsignatura(
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_asignatura"),
                        rs.getDouble("calificacion")
                ));
            }
        }
    }

    // Ejercicio 1: Consulta de estudiantes por casa (Gryffindor)
    public void mostrarEstudiantesPorCasa(String nombreCasa) {
        for (Estudiante estudiante : estudiantes) {
            casas.stream()
                    .filter(c -> c.getIdCasa() == estudiante.getIdCasa() && c.getNombreCasa().equals(nombreCasa))
                    .findFirst()
                    .ifPresent(casa -> System.out.println(estudiante.getNombre() + " " + estudiante.getApellido()));
        }
    }

    // Ejercicio 2: Listado de todas las asignaturas obligatorias
    public void obtenerAsignaturasObligatorias() {
        asignaturas.stream()
                .filter(Asignatura::isObligatoria)
                .forEach(asignatura -> System.out.println(asignatura.getNombreAsignatura()));
    }

    // Ejercicio 3: Imprimir la mascota de un estudiante
    // Método para imprimir la mascota de un estudiante específico
    public void mostrarMascotaDeEstudiante(String nombre, String apellido) {
        Estudiante estudiante = estudiantes.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre) && e.getApellido().equalsIgnoreCase(apellido))
                .findFirst().orElse(null);

        if (estudiante != null) {
            Mascota mascota = mascotas.stream()
                    .filter(m -> m.getIdEstudiante() == estudiante.getIdEstudiante())
                    .findFirst().orElse(null);
            if (mascota != null) {
                System.out.println("La mascota de " + nombre + " " + apellido + " es: " + mascota.getNombreMascota());
            } else {
                System.out.println(nombre + " " + apellido + " no tiene mascota.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
    /*
    // Método MEJORADO CON CHATGPT para imprimir la mascota de un estudiante específico
    public void mostrarMascotaDeEstudiante(String nombre, String apellido) {
        estudiantes.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre) && e.getApellido().equalsIgnoreCase(apellido))
                .findFirst()
                .ifPresentOrElse(estudiante -> {
                    mascotas.stream()
                            .filter(m -> m.getIdEstudiante() == estudiante.getIdEstudiante())
                            .findFirst()
                            .ifPresentOrElse(mascota -> System.out.println("La mascota de " + nombre + " " + apellido + " es: " + mascota.getNombreMascota()),
                                    () -> System.out.println(nombre + " " + apellido + " no tiene mascota."));
                }, () -> System.out.println("Estudiante " + nombre + " " + apellido + " no encontrado."));
    }

     */

    //Ejercicio 4: Listado de estudiantes sin mascota
// Método para imprimir la lista de estudiantes sin mascota
    public void mostrarEstudiantesSinMascota() {
        estudiantes.stream()
                .filter(estudiante -> mascotas.stream()
                        .noneMatch(mascota -> mascota.getIdEstudiante() == estudiante.getIdEstudiante()))
                .forEach(estudiante -> System.out.println("- " + estudiante.getNombre() + " " + estudiante.getApellido()));
    }


    // Método para imprimir el promedio de calificaciones de un estudiante dado su nombre y apellido
    public void mostrarPromedioCalificaciones(String nombre, String apellido) {
        estudiantes.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre) && e.getApellido().equalsIgnoreCase(apellido))
                .findFirst()
                .ifPresentOrElse(estudiante -> {
                    double promedio = estudianteAsignaturas.stream()
                            .filter(ea -> ea.getIdEstudiante() == estudiante.getIdEstudiante())
                            .mapToDouble(EstudianteAsignatura::getCalificacion)//ea -> ea.getCalificacion()
                            .average()
                            .orElse(0.0);
                    System.out.println("El promedio de calificaciones de " + nombre + " " + apellido + " es: " + promedio);
                }, () -> System.out.println("Estudiante no encontrado."));
    }

    // Ejercicio 6: Número de estudiantes por casa
    public void contarEstudiantesPorCasa() {
        estudiantes.stream()
                .collect(Collectors.groupingBy(estudiante -> obtenerNombreCasaPorId(estudiante.getIdCasa()),
                        Collectors.counting()))
                .forEach((nombreCasa, conteo) -> System.out.println(nombreCasa + ": " + conteo));
    }
    private String obtenerNombreCasaPorId(int idCasa) {
        return casas.stream()
                .filter(casa -> casa.getIdCasa() == idCasa)
                .map(Casa::getNombreCasa)
                .findFirst()
                .orElse("Casa desconocida");
    }


    /*
    public void contarEstudiantesPorCasa() {
        Map<String, Integer> conteoEstudiantesPorCasa = new HashMap<>();

        for (Estudiante estudiante : estudiantes) {
            String nombreCasa = obtenerNombreCasaPorId(estudiante.getIdCasa());
            conteoEstudiantesPorCasa.put(nombreCasa, conteoEstudiantesPorCasa.getOrDefault(nombreCasa, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : conteoEstudiantesPorCasa.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    // Método auxiliar para obtener el nombre de la casa por ID
    private String obtenerNombreCasaPorId(int idCasa) {
        for (Casa casa : casas) {
            if (casa.getIdCasa() == idCasa) {
                return casa.getNombreCasa();
            }
        }
        return "Casa desconocida";
    }

     */

    // 7. Estudiantes matriculados en una asignatura específica
    public void listarEstudiantesPorAsignatura(String nombreAsignatura) {
        estudianteAsignaturas.stream()
                .filter(ea -> ea.getIdAsignatura() == obtenerIdAsignaturaPorNombre(nombreAsignatura))
                .map(ea -> obtenerEstudiantePorId(ea.getIdEstudiante()))
                .filter(Objects::nonNull)  // Filtrar estudiantes nulos
                .forEach(estudiante -> System.out.println(estudiante.getNombre() + " " + estudiante.getApellido()));
    }
    private int obtenerIdAsignaturaPorNombre(String nombreAsignatura) {
        return asignaturas.stream()
                .filter(asignatura -> asignatura.getNombreAsignatura().equalsIgnoreCase(nombreAsignatura))
                .map(Asignatura::getIdAsignatura)
                .findFirst()
                .orElse(-1); // Devuelve -1 si no se encontró
    }
    private Estudiante obtenerEstudiantePorId(int idEstudiante) {
        return estudiantes.stream()
                .filter(estudiante -> estudiante.getIdEstudiante() == idEstudiante)
                .findFirst()
                .orElse(null); // Devuelve null si no se encontró
    }



    /*
    public void listarEstudiantesPorAsignatura(String nombreAsignatura) {
        List<Estudiante> estudiantesMatriculados = new ArrayList<>();
        int idAsignatura = obtenerIdAsignaturaPorNombre(nombreAsignatura);

        if (idAsignatura == -1) {
            System.out.println("La asignatura " + nombreAsignatura + " no existe.");
            return;
        }

        for (EstudianteAsignatura ea : estudianteAsignaturas) {
            if (ea.getIdAsignatura() == idAsignatura) {
                Estudiante estudiante = obtenerEstudiantePorId(ea.getIdEstudiante());
                if (estudiante != null) {
                    estudiantesMatriculados.add(estudiante);
                }
            }
        }
        for (Estudiante estudiante : estudiantesMatriculados) {
            System.out.println(estudiante.getNombre() + " " + estudiante.getApellido());
        }
    }

    // Método auxiliar para obtener el ID de una asignatura por nombre
    private int obtenerIdAsignaturaPorNombre(String nombreAsignatura) {
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getNombreAsignatura().equalsIgnoreCase(nombreAsignatura)) {
                return asignatura.getIdAsignatura();
            }
        }
        return -1; // Significa que no se encontró
    }

    // Método auxiliar para obtener un estudiante por ID
    private Estudiante obtenerEstudiantePorId(int idEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getIdEstudiante() == idEstudiante) {
                return estudiante;
            }
        }
        return null; // Significa que no se encontró
    }

     */

/*
    // 8. Insertar un nuevo estudiante
    public void insertarEstudiante(String nombre, String apellido, int idCasa, int anioCurso, Date fechaNacimiento) throws SQLException {
        String query = "INSERT INTO Estudiante (nombre, apellido, idCasa, anioCurso, fechaNacimiento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setInt(3, idCasa);
            stmt.setInt(4, anioCurso);
            stmt.setDate(5, fechaNacimiento);
            stmt.executeUpdate();
            System.out.println("Estudiante insertado con éxito.");
        }
    }

    // 9. Modificar el aula de una asignatura
    public void modificarAulaAsignatura(int idAsignatura, String nuevaAula) throws SQLException {
        String query = "UPDATE Asignatura SET aula = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevaAula);
            stmt.setInt(2, idAsignatura);
            stmt.executeUpdate();
            System.out.println("Aula modificada con éxito.");
        }
    }

    // 10. Desmatricular a un estudiante de una asignatura
    public void desmatricularEstudianteDeAsignatura(int idEstudiante, int idAsignatura) throws SQLException {
        String query = "DELETE FROM Estudiante_Asignatura WHERE idEstudiante = ? AND idAsignatura = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idAsignatura);
            stmt.executeUpdate();
            System.out.println("Estudiante desmatriculado con éxito.");
        }
    }

 */
}
