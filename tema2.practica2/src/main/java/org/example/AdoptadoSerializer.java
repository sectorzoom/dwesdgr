package org.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class AdoptadoSerializer extends StdSerializer<Boolean> {


    public AdoptadoSerializer(Class<Boolean> t) {
        super(t);
    }

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String adoptado = value ? "si" : "no";
        gen.writeString(adoptado);
    }
}
