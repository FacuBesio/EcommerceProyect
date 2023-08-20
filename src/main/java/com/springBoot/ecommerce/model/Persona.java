package com.springBoot.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* 
A través de la anotación @Entity (de JPA) vamos a indicar a SpringBoot que se deberá generar en la base de datos 
un Tabla con las mismas caraterísticas. Es decir, Spring se va a engarcar de inyectar en la base de datos, las 
sentencias SQL necesarias para crear un una replica de la Clase Java, en una Tabla SQL en la base datos. Desde
JPA se van a generar las sentencias que necesita Spring para inyectar en la Base de Datos. La base de datos se 
declara en el archivo 'application.properties' generalmente ubicado en la carpeta .../src/main/resources.
*/
@Entity
@Table(name = "personas")
public class Persona {
	
	@Id // Declaramos cual va a ser la Clave Primaria de la Tabla.
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idPersona;
	@Column(name = "col_nombre", length = 50)
	private String nombre;
	
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
