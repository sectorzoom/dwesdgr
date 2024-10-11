package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveInfo {
    @JsonProperty("move")
    private Move move;

    @Override
    public String toString() {
        return "MoveInfo{" +
                "move=" + move +
                '}';
    }
}