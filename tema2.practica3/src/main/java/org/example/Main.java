package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AnimalShelter animalShelter = new AnimalShelter(new ArrayList<>());
        Path RUTA_JSON = Path.of("C:/Users/david/Documents/2 DAW/Desarrollo Web en Entorno Servidor/Ejercicios/dwesdgr/tema2.practica3/src/main/resources/protectoraDeAnimales.json");
        loadJSON(animalShelter, RUTA_JSON);

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    addAnimal(animalShelter);
                    break;
                case 2:
                    deleteAnimal(animalShelter);
                    break;
                case 3:
                    searchAnimal(animalShelter);
                    break;
                case 4:
                    showAllAnimals(animalShelter);
                    break;
                case 5:
                    saveJSON(animalShelter, RUTA_JSON);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    public static AnimalShelter leerAnimalesJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), AnimalShelter.class);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
            return new AnimalShelter(new ArrayList<>()); // Retorna una lista vacía si hay un error
        }
    }

    public static void escribirAnimalesJson(AnimalShelter listaAnimales, Path ruta) {
        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), listaAnimales);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    private static void loadJSON(AnimalShelter animalShelter, Path RUTA_JSON) {
        try {
            AnimalShelter loadedShelter = leerAnimalesJson(RUTA_JSON);
            animalShelter.setAnimales(loadedShelter.getAnimales());
            System.out.println("Información cargada desde el archivo JSON.");
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar la información. Archivo no encontrado o vacío.");
        }
    }


    private static void saveJSON(AnimalShelter animalShelter, Path RUTA_JSON) {
        escribirAnimalesJson(animalShelter, RUTA_JSON);
        System.out.println("Información guardada en el archivo JSON.");
    }



    private static int calcularSiguienteId(AnimalShelter animalShelter) {
        return animalShelter.getAnimales().stream()
                .mapToInt(Animal::getId)
                .max()
                .orElse(0) + 1; // Si no hay animales, empieza en 1
    }



    public static void addAnimal(AnimalShelter animalShelter) {
        Animal animal = new Animal();
        // Calcular el siguiente ID
        int nextId = calcularSiguienteId(animalShelter);
        animal.setId(nextId);
        System.out.print("Nombre: ");
        animal.setName(scanner.nextLine());
        System.out.print("Especie: ");
        animal.setSpecie(scanner.nextLine());
        System.out.print("Edad: ");
        animal.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.print("Sexo (Macho/Hembra): ");
        animal.setGenre(scanner.nextLine());

        // Captura de la fecha de ingreso
        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fechaInput = scanner.nextLine();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        formatoFecha.setLenient(false);

        try {
            Date fechaIngreso = formatoFecha.parse(fechaInput);
            animal.setDateOfEntry(fechaIngreso); // Establecer la fecha en el objeto Animal
            System.out.println("Fecha de ingreso: " + formatoFecha.format(fechaIngreso));
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar el formato YYYY-MM-DD.");
            return; // Salir si la fecha es inválida
        }

        // Captura de adopción
        System.out.print("¿Adoptado? (Sí/No): ");
        String respuesta = scanner.nextLine().trim();
        if ("si".equalsIgnoreCase(respuesta)) {
            animal.setAdopted(true);
        } else if ("no".equalsIgnoreCase(respuesta)) {
            animal.setAdopted(false);
        } else {
            System.out.println("Respuesta inválida. Por favor, introduce 'si' o 'no'.");
            return; // Salir si la respuesta es inválida
        }

        animalShelter.getAnimales().add(animal);
        System.out.println("Animal añadido correctamente.");
    }





    private static void deleteAnimal(AnimalShelter animalShelter) {
        System.out.print("Ingrese ID del animal a borrar: ");
        int id = scanner.nextInt();
        // Buscamos el animal por ID
        Optional<Animal> animalAEliminar = animalShelter.getAnimales().stream()
                .filter(animal -> animal.getId() == id)
                .findFirst();
        if (animalAEliminar.isPresent()) {
            Animal animal = animalAEliminar.get();
            if (animal.isAdopted()) {
                animalShelter.getAnimales().remove(animal);
                System.out.println("Animal borrado.");
            } else {
                System.out.println("No se puede borrar, el animal no está adoptado.");
            }
        } else {
            System.out.println("No se encontró ningún animal con ese ID.");
        }
    }


    private static void searchAnimal(AnimalShelter animalShelter) {
        System.out.print("Ingrese ID del animal a consultar: ");
        int id = scanner.nextInt();
        animalShelter.getAnimales().stream()
                .filter(animal -> animal.getId() == id)
                .findFirst()
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Animal no encontrado."));
    }

    private static void showAllAnimals(AnimalShelter animalShelter) {
        if (animalShelter.getAnimales().isEmpty()) {
            System.out.println("No hay animales en adopción.");
        } else {
            animalShelter.getAnimales().forEach(System.out::println);
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de la Protectora de Animales ---");
        System.out.println("1. Añadir animal");
        System.out.println("2. Borrar animal");
        System.out.println("3. Consultar animal");
        System.out.println("4. Mostrar todos los animales");
        System.out.println("5. Guardar información en fichero JSON");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
