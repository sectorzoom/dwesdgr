import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profesor {
    private int idProfesor;
    private String nombre;
    private String apellido;
    private Asignatura asignatura; // Relación con la clase Asignatura
    private java.util.Date fechaInicio;

    // Constructor
    public Profesor(int idProfesor, String nombre, String apellido, Asignatura asignatura, java.util.Date fechaInicio) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.asignatura = asignatura;
        this.fechaInicio = fechaInicio;
    }

    // Getters y Setters
    public int getIdProfesor() { return idProfesor; }
    public void setIdProfesor(int idProfesor) { this.idProfesor = idProfesor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Asignatura getAsignatura() { return asignatura; }
    public void setAsignatura(Asignatura asignatura) { this.asignatura = asignatura; }

    public java.util.Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(java.util.Date fechaInicio) { this.fechaInicio = fechaInicio; }
}
