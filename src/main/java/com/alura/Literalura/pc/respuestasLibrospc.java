package com.alura.Literalura.pc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class respuestasLibrospc {

    @JsonProperty("results")
    private List<Libropc> libros;

    public List<Libropc> getLibros() {
        return libros;
    }

    public void setLibros(List<Libropc> libros) {
        this.libros = libros;
    }
}