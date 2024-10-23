import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Casa {
    private int idCasa;
    private String nombreCasa;
    private String fundador;
    private String jefeCasa;
    private String fantasma;

    // Constructor
    public Casa(int idCasa, String nombreCasa, String fundador, String jefeCasa, String fantasma) {
        this.idCasa = idCasa;
        this.nombreCasa = nombreCasa;
        this.fundador = fundador;
        this.jefeCasa = jefeCasa;
        this.fantasma = fantasma;
    }
}
