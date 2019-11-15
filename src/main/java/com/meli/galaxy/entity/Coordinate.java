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

import javassist.bytecode.ConstantAttribute;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Table(name = "coordinate")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class Coordinate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="date", columnDefinition = "DATE not null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = Constantconfig.SYSTEM_TIMEZONE)
	private Date date;
	
	@Column(name="year", columnDefinition = "int not null")
	private Integer year;
	
	@Column(name="latitude", columnDefinition = "varchar(255)")
	private String latitude;
	
	@Column(name="longitude", columnDefinition = "varchar(255)")
	private String longitude;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planet_id", columnDefinition = "int not null")
	private Planet planet;

	
}