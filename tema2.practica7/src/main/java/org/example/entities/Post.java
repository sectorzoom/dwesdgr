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
@BsonDiscriminator(value = "post", key = "_cls")
public class Post {
    @BsonId
    private ObjectId id; // identificador único del post

    @BsonProperty("title")
    private String title; // título del post

    @BsonProperty("content")
    private String content; // contenido del post

    @BsonProperty("publishedDate")
    private LocalDate publishedDate; // fecha de creación del post

    @BsonProperty("likes")
    private int likes; // cantidad de likes del post

    @BsonProperty("comments")
    private List<String> comments; // lista de comentarios del post

    public Post() {
        // Constructor vacío necesario para MongoDB
    }

    @Override
    public String toString() {
        String string = title + "\n" + publishedDate + "\n" + likes + " likes\n" + content + "\n";
        for (String comment : comments) {
            string += " - " + comment + "\n";
        }
        return string;
    }

}
