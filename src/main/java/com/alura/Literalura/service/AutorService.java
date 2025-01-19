package com.alura.Literalura.service;

import com.alura.Literalura.modelo.Autores;
import com.alura.Literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autores> listarAutores(){
        return autorRepository.findAllConLibros();
    }

    public List<Autores> listarAutoresVivosEnAno(int ano){
        return autorRepository.findAutoresVivosEnAnoConLibros(ano);
    }
    public Autores CrearAutor(Autores autor){
        return autorRepository.save(autor);
    }
    public Optional<Autores> obtenerAutorPorId(Long id){
        return autorRepository.findById(id);
    }
    public Optional<Autores> obtenerAutorPorNombre(String nombre){
        return autorRepository.findByNombre(nombre);
    }

    public Autores actualizarAutor(Long id, Autores autorDetalles){
        Autores autores = autorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Autor no Encontrado"));
        autores.setNombre(autorDetalles.getNombre());
        autores.setAnoFallecimiento(autorDetalles.getAnoFallecimiento());
        autores.setAnoNacimiento(autorDetalles.getAnoNacimiento());
        return autorRepository.save(autores);
    }
    public void eliminarAutor(Long id){
        autorRepository.deleteById(id);
    }
}
