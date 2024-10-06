package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class BooleanFromYesNoDeserializer extends JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getText().trim().toLowerCase();

        // Maneja "sí", "si" y valores booleanos explícitos
        if ("sí".equals(value) || "si".equals(value) || "true".equals(value)) {
            return true;
        } else if ("no".equals(value) || "false".equals(value)) {
            return false;
        }

        // En caso de valor inesperado, lanza una excepción o devuelve false por defecto
        throw new IOException("Valor inesperado para campo adoptado: " + value);
    }
}


