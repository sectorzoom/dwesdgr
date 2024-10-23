import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mascota {
    private int idMascota;
    private String nombreMascota;
    private String especie;
    private int idEstudiante; // Relación con la clase Estudiante

    // Constructor
    public Mascota(int idMascota, String nombreMascota, String especie, int idEstudiante) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.idEstudiante = idEstudiante;
    }
}
