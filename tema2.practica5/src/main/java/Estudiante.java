import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
public class Estudiante {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private int idCasa; // Relación con la clase Casa
    private int año_Curso;
    private Date fechaNacimiento;
}

