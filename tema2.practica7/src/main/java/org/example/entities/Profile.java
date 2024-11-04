package org.example.entities;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@BsonDiscriminator(value = "profile", key = "_cls")
public class  Profile {
    @BsonId
    private ObjectId id; // identificador único del perfil

    @BsonProperty("name")
    private String name; // nombre del perfil

    @BsonProperty("status")
    private String status; // estado del perfil

    @BsonProperty("age")
    private int age; // edad del perfil

    @BsonProperty("since")
    private LocalDate since; // fecha de creación del perfil

    @BsonProperty("posts")
    private List<Post> posts; // lista de publicaciones del perfil

    public Profile() {
        // Constructor vacío necesario para MongoDB
    }
    @Override
    public String toString() {
        String string = "-".repeat(20) + "\n" + name + "\nUsuario desde: " + since + "\nEstado:" + status + "\nEdad: " + age + " años\n";
        if (posts != null) {
            string += "Publicaciones:\n";
            for (Post post : posts) {
                string += post + "\n";
            }
        } else {
            string += "No ha publicado nada todavía.\n";
        }
        string += "-".repeat(20);
        return string;
    }
}

