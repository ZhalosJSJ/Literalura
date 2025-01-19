package com.alura.Literalura.principal;

import com.alura.Literalura.pc.Autorpc;
import com.alura.Literalura.pc.Libropc;
import com.alura.Literalura.pc.respuestasLibrospc;
import com.alura.Literalura.modelo.Autores;
import com.alura.Literalura.modelo.Libros;
import com.alura.Literalura.service.AutorService;
import com.alura.Literalura.service.ConsumoAPI;
import com.alura.Literalura.service.ConvierteDatos;
import com.alura.Literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ConvierteDatos convierteDatos;

    private static final String BASE_URL = "https://gutendex.com/books/";

    // Muestra el menú principal de la aplicación y maneja las opciones seleccionadas por el usuario.
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarOpciones();
            opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo(scanner);
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos(scanner);
                    break;
                case 5:
                    listarLibrosPorIdioma(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("--- LITERALURA ---");
        System.out.println("1 - Buscar libro por título");
        System.out.println("2 - Listar libros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos en un año");
        System.out.println("5 - Listar libros por idioma");
        System.out.println("0 - Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int obtenerOpcion(Scanner scanner) {
        return scanner.nextInt();
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        try {
            String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String json = consumoAPI.obtenerDatos(BASE_URL + "?search=" + encodedTitulo);
            respuestasLibrospc respuestasLibrosPC = convierteDatos.obtenerDatos(json, respuestasLibrospc.class);  // Cambiado a minúsculas
            List<Libropc> librosPC = respuestasLibrosPC.getLibros();  // Cambiado a minúsculas

            if (librosPC.isEmpty()) {
                System.out.println("Libro no encontrado en la API");
            } else {
                procesarLibrosEncontrados(librosPC, titulo);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void procesarLibrosEncontrados(List<Libropc> librosPC, String titulo) {  // Cambiado a minúsculas
        boolean libroRegistrado = false;

        for (Libropc libroPC : librosPC) {  // Cambiado a minúsculas
            if (libroPC.getTitulo().equalsIgnoreCase(titulo)) {
                Optional<Libros> libroExistente = libroService.obtenerLibrosPorTitulo(titulo);
                if (libroExistente.isPresent()) {
                    System.out.println("El libro ya está registrado: " + libroExistente.get());
                } else {
                    // Lógica para registrar el libro si no está presente
                    System.out.println("Libro encontrado: " + libroPC);  // Cambiado a minúsculas
                    // Código para registrar el libro en la base de datos
                }
                libroRegistrado = true;
            }
        }

        if (!libroRegistrado) {
            System.out.println("No se encontró el libro registrado con el título: " + titulo);
        }
    }

    private void listarLibrosRegistrados() {
        // Implementar la lógica para listar libros registrados
    }

    private void listarAutoresRegistrados() {
        // Implementar la lógica para listar autores registrados
    }

    private void listarAutoresVivos(Scanner scanner) {
        // Implementar la lógica para listar autores vivos en un año
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        // Implementar la lógica para listar libros por idioma
    }
}
