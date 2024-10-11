package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalShelter {

    // Getter y Setter
    @JsonProperty("animales")
    private List<Animal> animales;


    @Override
    public String toString() {
        return "ListaAnimales{" +
                "animales=" + animales +
                '}';
    }
}
