package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AnimalShelter {
    private final List<Animal> animales = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public AnimalShelter() {}

    public List<Animal> getAnimales() {
        return animales;
    }

    public void addAnimal() {
        Animal animal = new Animal();
        System.out.print("ID: ");
        animal.setId(scanner.nextLine());
        System.out.print("Nombre: ");
        animal.setName(scanner.nextLine());
        System.out.print("Especie: ");
        animal.setSpecie(scanner.nextLine());
        System.out.print("Edad: ");
        animal.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.print("Sexo (Macho/Hembra): ");
        animal.setGenre(scanner.nextLine());

        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fechaInput = scanner.nextLine();
        // Crear un formateador de fechas
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        formatoFecha.setLenient(false); // Para lanzar una excepción en caso de formato incorrecto
        try {
            // Convertir la cadena de texto a un objeto Date
            Date fechaIngreso = formatoFecha.parse(fechaInput);
            // Imprimir la fecha en consola
            System.out.println("Fecha de ingreso: " + formatoFecha.format(fechaIngreso));
            animal.setDateOfEntry(fechaIngreso); // Agregar la fecha al objeto Animal
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar el formato YYYY-MM-DD.");
        }

        System.out.print("¿Adoptado? (Si/No): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        if (respuesta.equals("sí") || respuesta.equals("si")) {
            animal.setAdopted(true);
        } else if (respuesta.equals("no")) {
            animal.setAdopted(false);
        } else {
            System.out.println("Respuesta inválida. Por favor, introduce 'sí' o 'no'.");
        }

        animales.add(animal);
        System.out.println("Animal añadido correctamente.");
    }

    public void deleteAnimal() {
        System.out.print("Introduce el ID del animal a borrar: ");
        String id = scanner.nextLine();
        animales.removeIf(animal -> animal.getId().equals(id));
        System.out.println("Animal borrado si existía.");
    }

    public void searchAnimal() {
        System.out.print("Introduce el ID del animal a consultar: ");
        String id = scanner.nextLine();
        animales.stream()
                .filter(animal -> animal.getId().equals(id))
                .forEach(animal -> {
                    System.out.println("Nombre: " + animal.getName());
                    System.out.println("Especie: " + animal.getSpecie());
                    System.out.println("Edad: " + animal.getAge());
                    System.out.println("Sexo: " + animal.getGenre());

                    // Formatear la fecha para mostrarla en formato yyyy/MM/dd
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
                    String fechaIngreso = formatoFecha.format(animal.getDateOfEntry());
                    System.out.println("Fecha de ingreso: " + fechaIngreso);

                    System.out.println("Adoptado: " + (animal.getAdopted() ? "sí" : "no"));
                });
    }

    public void showAllAnimals() {
        animales.forEach(animal -> {
            System.out.println("ID: " + animal.getId());
            System.out.println("Nombre: " + animal.getName());
            System.out.println("Especie: " + animal.getSpecie());
            System.out.println("Edad: " + animal.getAge());
            System.out.println("Sexo: " + animal.getGenre());

            // Formatear y mostrar la fecha
            SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy/MM/dd"); // Definir el formato deseado
            String fechaFormateada = formatoSalida.format(animal.getDateOfEntry());
            System.out.println("Fecha de ingreso: " + fechaFormateada); // Imprimir la fecha formateada

            System.out.println("Adoptado: " + (animal.getAdopted() ? "sí" : "no"));
            System.out.println("---------------------");
        });
    }
}
