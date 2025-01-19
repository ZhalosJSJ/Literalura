package com.alura.Literalura.modelo;

import jakarta.persistence.*;

@Entity
@Table (name = "Libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String titulo;
    private String idioma;
    private int numeroDescargas;
    @ManyToOne
    @JoinColumn(name ="autor_id")
    private Autores Autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autores getAutor() {
        return Autor;
    }

    public void setAutor(Autores autor) {
        Autor = autor;
    }

    @Override
    public String toString() {
        return "LIBRO -----\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (Autor != null ? Autor.getNombre() : "Desconocido") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas: " + numeroDescargas + "\n" +
                "--------------------";
    }
}