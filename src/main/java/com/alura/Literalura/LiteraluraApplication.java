package com.alura.Literalura;

import com.alura.Literalura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private final Principal principal;

//	 Constructor de la clase LiteraluraApplication.
//	 Inicializa la aplicación con una instancia de Principal.
//	 principal Instancia de la clase Principal.

	public LiteraluraApplication(Principal principal) {
		this.principal = principal;
	}

//	 Metodo principal que inicia la aplicación Spring Boot.
//	 Argumentos de la línea de comandos.

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

//	 Metodo que se ejecuta después de que la aplicación ha iniciado.
//	 Muestra el menú principal de la aplicación
//	 Argumentos de la línea de comandos.
//	 Throws Exception Si ocurre algún error durante la ejecución.

	@Override
	public void run(String... args) throws Exception {
		principal.mostrarMenu();
	}
}