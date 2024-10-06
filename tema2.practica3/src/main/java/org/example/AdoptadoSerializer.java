package org.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AdoptadoSerializer extends StdSerializer<Boolean> {

    // Constructor por defecto
    public AdoptadoSerializer() {
        this(null);
    }

    // Constructor que acepta una clase (se requiere para StdSerializer)
    public AdoptadoSerializer(Class<Boolean> t) {
        super(t);
    }

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // Serialización personalizada: por ejemplo, "Sí" para true y "No" para false
        gen.writeString(value ? "Sí" : "No");
    }
}
