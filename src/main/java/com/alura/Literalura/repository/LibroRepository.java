package com.alura.Literalura.repository;

import com.alura.Literalura.modelo.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libros, Long>{
        Optional<Libros> findByTituloIgnoreCase(String titulo);
    @Query("SELECT l fROM libro l WHERE l.idioma= :idioma")
    List<Libros> findByIdioma(@Param("idioma") String idioma);
}