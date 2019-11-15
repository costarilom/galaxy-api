package com.meli.galaxy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//Tabla donde alojo los planetas con sus coordenadas de inicio y su forma de desplazamiento

@Table(name = "planet")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class Planet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="code", columnDefinition = "varchar(3) not null")
	private String code;

	@Column(name="name", columnDefinition = "varchar(255) not null")
	private String name;
	
	//Eje Y
	@Column(name="initial_latitude", columnDefinition = "varchar(255) not null")
	private String initialLatitude;
	
	//Eje X
	@Column(name="initial_longitude", columnDefinition = "varchar(255) not null")
	private String initialLongitude;
	
	@Column(name="displacement", columnDefinition = "int not null")
	private Integer displacement;
	
	@Column(name="direction_of_rotation", columnDefinition = "varchar(3) not null")
	private String directionOfRotation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitialLatitude() {
		return initialLatitude;
	}

	public void setInitialLatitude(String initialLatitude) {
		this.initialLatitude = initialLatitude;
	}

	public String getInitialLongitude() {
		return initialLongitude;
	}

	public void setInitialLongitude(String initialLongitude) {
		this.initialLongitude = initialLongitude;
	}

	public Integer getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Integer displacement) {
		this.displacement = displacement;
	}

	public String getDirectionOfRotation() {
		return directionOfRotation;
	}

	public void setDirectionOfRotation(String directionOfRotation) {
		this.directionOfRotation = directionOfRotation;
	}


	
}