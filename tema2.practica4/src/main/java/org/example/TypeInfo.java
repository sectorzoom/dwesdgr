package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeInfo {
    @JsonProperty("type")
    private Type type;

    @Override
    public String toString() {
        return "TypeInfo{" +
                "type=" + type +
                '}';
    }
}