package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.entities.Post;
import org.example.entities.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBManager {
    MongoCollection<Profile> profiles; // Colección de perfiles
    Profile myProfile; // Mi perfil

    public MongoDBManager(String uri, String databaseName, String collectionName) {
        MongoClient mongoClient;
        try {
            mongoClient = MongoClients.create(uri);
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);

            profiles = database.getCollection(collectionName, Profile.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createProfile(String name, String status, int age) {
        LocalDate now = LocalDate.now();
        Profile profile = new Profile();
        profile.setName(name);
        profile.setStatus(status);
        profile.setAge(age);
        profile.setSince(now);
        profile.setPosts(new ArrayList<>());

        profiles.insertOne(profile);
        myProfile = profile;
        System.out.println("Perfil creado para " + name);
    }

    public boolean loadProfile(String name) {
        return (myProfile = profiles.find(eq("name", name)).first()) != null;
    }



    public void publishPost(String title, String content) {
        if (myProfile != null) {
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setPublishedDate(LocalDate.now());
            post.setLikes(0);
            post.setComments(new ArrayList<>());

            myProfile.getPosts().add(post);
            profiles.replaceOne(eq("_id", myProfile.getId()), myProfile);
            System.out.println("Publicación agregada.");
        } else {
            System.out.println("Primero debes crear tu perfil.");
        }
    }


    public void updateStatus(String status) {
        if (myProfile != null) {
            myProfile.setStatus(status);
            profiles.replaceOne(eq("_id", myProfile.getId()), myProfile);
            System.out.println("Estado actualizado.");
        } else {
            System.out.println("Primero debes crear tu perfil.");
        }
    }


    public void deleteProfile() {
        if (myProfile != null) {
            profiles.deleteOne(eq("_id", myProfile.getId()));
            System.out.println("Perfil eliminado.");
            myProfile = null;
        } else {
            System.out.println("Primero debes crear tu perfil.");
        }
    }

    public void showProfiles() {
        profiles.find().forEach(System.out::println);
    }

    public void showPosts(String profileName) {
        Profile profile = profiles.find(eq("name", profileName)).first();
        if (profile != null) {
            System.out.println(profile);
        } else {
            System.out.println("Perfil no encontrado.");
        }
    }


    public void likePost(String profileName, String title) {
        Profile profile = profiles.find(eq("name", profileName)).first();
        if (profile != null) {
            for (Post post : profile.getPosts()) {
                if (post.getTitle().equals(title)) {
                    post.setLikes(post.getLikes() + 1);
                    profiles.replaceOne(eq("_id", profile.getId()), profile);
                    System.out.println("Like agregado.");
                } else{
                    System.out.println("Post no encontrado.");
                }
            }
        } else {
            System.out.println("Perfil no encontrado.");
        }
    }


    public void commentPost(String profileName, String title, String comment) {
        Profile profile = profiles.find(eq("name", profileName)).first();
        if (profile == null) {
            System.out.println("Perfil no encontrado.");
        } else {
            for (Post post : profile.getPosts()) {
                if (post.getTitle().equals(title)) {
                    post.getComments().add(comment);
                    profiles.replaceOne(eq("_id", profile.getId()), profile);
                    System.out.println("Comentario agregado.");
                    return;
                }else{
                    System.out.println("Post no encontrado.");
                }
            }
        }
    }


    public void showProfileStats() {
        if (myProfile != null) {
            int totalLikes = myProfile.getPosts().stream().mapToInt(post -> post.getLikes()).sum();
            int totalComments = myProfile.getPosts().stream().mapToInt(post -> post.getComments().size()).sum();

            System.out.println("Estadísticas de tu perfil:");
            System.out.println("Número de publicaciones: " + myProfile.getPosts().size());
            System.out.println("Total de likes: " + totalLikes);
            System.out.println("Total de comentarios: " + totalComments);
        } else{
            System.out.println("Primero debes crear tu perfil.");
        }
    }

    public void showAllStats() {
        List<Profile> profilesList = profiles.find().into(new ArrayList<>());
        int totalProfiles = profilesList.size();
        int totalPosts = profilesList.stream().mapToInt(profile -> profile.getPosts().size()).sum();
        int totalLikes = profilesList.stream().flatMap(profile -> profile.getPosts().stream()).mapToInt(post -> post.getLikes()).sum();
        int totalComments = profilesList.stream().flatMap(profile -> profile.getPosts().stream()).mapToInt(post -> post.getComments().size()).sum();

        System.out.println("Estadísticas de la red social:");
        System.out.println("Número total de perfiles: " + totalProfiles);
        System.out.println("Número total de publicaciones: " + totalPosts);
        System.out.println("Número total de likes: " + totalLikes);
        System.out.println("Número total de comentarios: " + totalComments);

        System.out.println("Usuarios mayores de edad:");
        profilesList.stream().filter(profile -> profile.getAge() >= 18).forEach(profile -> System.out.println(profile.getName()));

        System.out.println("Top 3 perfiles con más publicaciones:");
        profilesList.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getPosts().size(), p1.getPosts().size()))
                .limit(3)
                .forEach(profile -> System.out.println(profile.getName() + ": " + profile.getPosts().size() + " publicaciones"));
    }
}
