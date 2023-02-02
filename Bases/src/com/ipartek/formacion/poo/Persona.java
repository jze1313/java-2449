package com.ipartek.formacion.poo;

import java.time.LocalDate;

public class Persona {
	// Constantes
	private static final String NOMBRE_POR_DEFECTO = "Anónimo";

	// Variables estáticas ("de clase")
	private static int mayoriaDeEdad = 18;

	// Variables de instancia
	private Long id;
	private String nombre;
	private String dni;
	private LocalDate fechaNacimiento;

	// Constructores
	public Persona(Long id, String nombre, String dni, LocalDate fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setDni(dni);
		setFechaNacimiento(fechaNacimiento);
	}

	public Persona(String nombre, String dni, LocalDate fechaNacimiento) {
		this(null, nombre, dni, fechaNacimiento);
	}

	public Persona(String nombre) {
		this(null, nombre, null, null);
	}

	public Persona() {
		this(null, NOMBRE_POR_DEFECTO, null, null);
	}

	// (Constructor de copia)
	public Persona(Persona persona) {
		this(persona.id, persona.nombre, persona.dni, persona.fechaNacimiento);
	}

	// Métodos de acceso / Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("No se puede dejar el nombre sin rellenar");
		}

		if (nombre.trim().length() == 0) {
			throw new IllegalArgumentException("No puedes dejar el nombre en blanco");
		}

		this.nombre = nombre.trim();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public static int getMayoriaDeEdad() {
		return mayoriaDeEdad;
	}

	public static void setMayoriaDeEdad(int mayoriaDeEdad) {
		if (mayoriaDeEdad < 0) {
			throw new IllegalArgumentException("No se puede tener una mayoría de edad negativa");
		}
		
		Persona.mayoriaDeEdad = mayoriaDeEdad;
	}

	// Métodos de instancia
	public int getEdad() {
		if (fechaNacimiento == null) {
			throw new IllegalArgumentException("No podemos calcular la edad porque no tenemos fecha de nacimiento");
		}

		return fechaNacimiento.until(LocalDate.now()).getYears();
	}

	public boolean isMayorDeEdad() {
		return getEdad() >= mayoriaDeEdad;
	}

	// ToString: resumen de la información del objeto en una línea
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento
				+ "]";
	}
}
