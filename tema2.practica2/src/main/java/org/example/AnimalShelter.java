package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AnimalShelter {

    @JsonProperty("animales")
    private List<Animal> animales;

    // Constructor
    public AnimalShelter(List<Animal> animales) {
        this.animales = animales;
    }
    public AnimalShelter() {}

    // Getter y Setter
    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }

    public AnimalShelter leerAnimalesXml(Path ruta) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(ruta.toFile(), AnimalShelter.class);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
            return new AnimalShelter(new ArrayList<>()); // Retorna una lista vacía si hay un error
        }
    }

    public void escribirAnimalesXml(AnimalShelter listaAnimales, Path ruta) {
        try {
            Files.deleteIfExists(ruta);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(ruta.toFile(), listaAnimales);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }

    @Override
    public String toString() {
        return "ListaAnimales{" +
                "animales=" + animales +
                '}';
    }
}
