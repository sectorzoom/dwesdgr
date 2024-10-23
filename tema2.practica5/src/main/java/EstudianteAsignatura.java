import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteAsignatura {
    int idEstudiante;
    int idAsignatura;// Relación con la clase Asignatura
    private double calificacion;

    // Constructor
    public EstudianteAsignatura(int idEstudiante, int idAsignatura, double calificacion) {
        this.idEstudiante = idEstudiante;
        this.idAsignatura = idAsignatura;
        this.calificacion = calificacion;
    }
}
