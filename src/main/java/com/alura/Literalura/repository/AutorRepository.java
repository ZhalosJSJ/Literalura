package com.alura.Literalura.repository;


import com.alura.Literalura.modelo.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autores, Long> {

    Optional<Autores> findByNombre(String nombre);

    @Query("SELECT a FROM Autores a LEFT JOIN FETCH a.libros WHERE (a.anoFallecimiento IS NULL OR a.anoFallecimiento > :ano) AND a.anoNacimiento <= : ano")
    List<Autores> findAutoresVivosEnAnoConLibros(@Param("ano")int ano);

    @Query("SELECT a FROM Autores a LEFT JOIN FETCH a.libros")
    List<Autores> findAllConLibros();

}
