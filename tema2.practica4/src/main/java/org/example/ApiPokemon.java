package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiPokemon {
    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        while (true) {
            System.out.println("Elige un Pokémon (nombre o ID) o escribe 'salir' para terminar:");
            String pokemonInput = sc.nextLine().toLowerCase();

            if (pokemonInput.equals("salir")) {
                System.out.println("¡Hasta luego!");
                break;
            }

            try {
                // Realizar la solicitud a la API
                JsonNode rootNode = objectMapper.readTree(new URL(API_URL + pokemonInput));
                Pokemon pokemon = objectMapper.readValue(rootNode.traverse(), Pokemon.class);
                System.out.println(pokemon);
            } catch (MalformedURLException e) {
                System.out.println("URL malformada.");
            } catch (IOException e) {
                System.out.println("Error al obtener los datos del Pokémon. Verifica el nombre o ID.");
            }
        }
    }
}
