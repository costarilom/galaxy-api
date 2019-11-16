package com.meli.galaxy.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meli.galaxy.config.Constantconfig;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

//Tabla donde dejo alojada la coordenada de cada planeta por dia


@Table(name = "coordinate")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class Coordinate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="date", columnDefinition = "DATE not null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = Constantconfig.SYSTEM_TIMEZONE)
	private Date date;
	
	@Column(name="year", columnDefinition = "int not null")
	private Integer year;
	
	//Eje Y
	@Column(name="latitude", columnDefinition = "varchar(255) not null")
	private String latitude;
	
	//Eje X
	@Column(name="longitude", columnDefinition = "varchar(255) not null")
	private String longitude;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planet_id", columnDefinition = "int not null")
	private Planet planet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	
	
}