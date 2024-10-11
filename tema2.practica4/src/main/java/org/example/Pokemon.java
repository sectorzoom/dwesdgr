package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    // Getters y setters ;
    @JsonProperty("name")
    private String name;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("height")
    private int height;
    @JsonProperty("types")
    private List<TypeInfo> types;
    @JsonProperty("moves")
    private List<MoveInfo> moves;

    @Override
    public String toString() {
        StringBuilder typesString = new StringBuilder();
        for (TypeInfo type : types) {
            typesString.append(type.getType().getName()).append(" ");
        }

        StringBuilder movesString = new StringBuilder();
        for (MoveInfo move : moves) {
            movesString.append(move.getMove().getName()).append(" ");
        }

        return "Pokemon{" +
                "\nname='" + name +
                "\nweight=" + weight +
                "\nheight=" + height +
                "\ntypes=" + typesString +
                "\nmoves=" + movesString +
                '}';
    }
}
