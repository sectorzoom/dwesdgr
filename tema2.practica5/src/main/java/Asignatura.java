import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asignatura {
    private int idAsignatura;
    private String nombreAsignatura;
    private String aula;
    private boolean obligatoria;

    // Constructor
    public Asignatura(int idAsignatura, String nombreAsignatura, String aula, boolean obligatoria) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.aula = aula;
        this.obligatoria = obligatoria;
    }

}
