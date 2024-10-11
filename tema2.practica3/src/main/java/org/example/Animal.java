package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @JsonProperty("id")
    private int id;  // Cambiado a int para id autonómico

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("especie")
    private String specie;

    @JsonProperty("edad")
    private int age;

    @JsonProperty("sexo")
    private String gender;

    @JsonProperty("fechaIngreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonProperty("adoptado")
    @JsonDeserialize(using = AdoptadoDeserializer.class)
    @JsonSerialize(using = AdoptadoSerializer.class)
    private boolean adopted;

    @Override
    public String toString() {
        // Usamos DateTimeFormatter para formatear LocalDate
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "id=" + id +
                ", nombre='" + name + '\'' +
                ", especie='" + specie + '\'' +
                ", edad=" + age +
                ", sexo='" + gender + '\'' +
                ", fechaIngreso=" + date.format(formatoFecha) + // Formateamos LocalDate
                ", adoptado='" + (adopted ? "Sí" : "No") + '\'';
    }

}
