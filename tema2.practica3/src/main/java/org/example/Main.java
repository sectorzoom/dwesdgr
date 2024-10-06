package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final String FICHERO_JSON = "C:/Users/david/Documents/2 DAW/Desarrollo Web en Entorno Servidor/Ejercicios/dwesdgr/tema2.practica3/src/main/resources/animales.json";
    private static AnimalShelter protectora = new AnimalShelter();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            showMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    cargarDatos();
                    break;
                case 2:
                    guardarDatos(protectora);
                    break;
                case 3:
                    protectora.addAnimal();
                    break;
                case 4:
                    protectora.deleteAnimal();
                    break;
                case 5:
                    protectora.searchAnimal();
                    break;
                case 6:
                    protectora.showAllAnimals();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    // Método para cargar datos desde un archivo JSON
    public static void cargarDatos() {
        try {
            protectora = objectMapper.readValue(new File(FICHERO_JSON), AnimalShelter.class); // Asigna a la variable estática
            protectora.getAnimales().forEach(animal -> System.out.println(animal.getName() + " cargado."));
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar el fichero JSON: " + e.getMessage());
        }
    }

    // Método para guardar datos en un archivo JSON
    public static void guardarDatos(AnimalShelter protectora) {
        try {
            objectMapper.writeValue(new File(FICHERO_JSON), protectora);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero JSON: " + e.getMessage());
        }
    }



    private static void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Cargar información de fichero JSON");
        System.out.println("2. Guardar información en fichero JSON");
        System.out.println("3. Añadir animal");
        System.out.println("4. Borrar animal");
        System.out.println("5. Consultar animal");
        System.out.println("6. Mostrar todos los animales");
        System.out.println("7. Salir");
        System.out.print("Elige una opción: ");
    }
}
