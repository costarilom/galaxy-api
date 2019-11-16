package com.meli.galaxy.config;

public interface Constantconfig {
	
	public static final String SYSTEM_TIMEZONE = "America/Argentina/Buenos_Aires";

	public static final int YEAR_MAX = 1;
	
	/*Coordenas iniciales para el sol*/
	public static final String SOL_X = "0";
	public static final String SOL_Y = "0";

	/*Code para la direccion de rotacion*/
	public static final String RIGHT = "RIG";
	public static final String LEFT = "LEF";
	
	/*Tipo de migracion de datos*/
	public static final String ALL = "ALL";
	public static final String DAY = "DAY";
	
	/*Estados de migracion de datos*/
	public static final String STATUS_PEN = "PEN";
	public static final String STATUS_END = "END";
}
