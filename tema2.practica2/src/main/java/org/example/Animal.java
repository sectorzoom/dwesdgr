package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    @JsonDeserialize(using = CustomDateDeserializer.class) // Para deserializar
    @JsonSerialize(using = CustomDateSerializer.class) // Para serializar
    private Date dateOfEntry;  // Cambiado a Date

    @JacksonXmlProperty(localName = "adoptado")
    @JsonDeserialize(using = AdoptadoDeserializer.class)
    @JsonSerialize(using = AdoptadoSerializer.class)
    private boolean adopted;

    public Animal() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGenre(String genre) {
        this.gender = genre;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "id=" + id +
                ", nombre='" + name + '\'' +
                ", especie='" + specie + '\'' +
                ", edad=" + age +
                ", sexo='" + gender + '\'' +
                ", fechaIngreso=" + sdf.format(dateOfEntry) +
                ", adoptado='" + (adopted ? "Si" : "No") + '\'';
    }
}
