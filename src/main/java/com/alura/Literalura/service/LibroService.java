package com.alura.Literalura.service;

import com.alura.Literalura.modelo.Libros;
import com.alura.Literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libros> listarLibros(){
        return libroRepository.findAll();
    }
    public List<Libros> listarLibrosPorIdioma(String idioma){
        return libroRepository.findByIdioma(idioma);
    }
    public Libros crearLibro(Libros libro){
        return libroRepository.save(libro);
    }

    public Optional<Libros> obtenerLibrosPorId(Long id){
        return libroRepository.findById(id);
    }
    public Optional<Libros> obtenerLibrosPorTitulo(String titulo){
        return libroRepository.findByTituloIgnoreCase(titulo);
    }
    public Libros actualizarLibro(Long id, Libros libroDetalles){
        Libros libro = libroRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setTitulo(libroDetalles.getTitulo());
        libro.setIdioma(libroDetalles.getIdioma());
        libro.setNumeroDescargas(libroDetalles.getNumeroDescargas());
        libro.setAutor(libroDetalles.getAutor());
        return libroRepository.save(libro);
    }
    public void eliminarLibro(Long id){
        libroRepository.deleteById(id);
    }
}
