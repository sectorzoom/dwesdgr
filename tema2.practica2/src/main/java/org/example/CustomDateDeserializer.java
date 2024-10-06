package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateDeserializer extends StdDeserializer<Date> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public CustomDateDeserializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String date = parser.getText();
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            throw new IOException("Error al deserializar la fecha: " + date, e);
        }
    }
}
