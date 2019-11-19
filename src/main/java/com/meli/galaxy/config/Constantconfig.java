
package com.meli.galaxy.config;

public interface Constantconfig {
	
	public static final String SYSTEM_TIMEZONE = "America/Argentina/Buenos_Aires";

	public static final int YEAR_MAX = 10;
	
	//Dia de inicio
	public static final String dateFrom = "15-11-2019";
	
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
	
	/*Code para tipos de climas*/
	public static final String DROUGHT_CODE = "DRO";
	public static final String RAINY_CODE = "RAI";
	public static final String INTENSITYPEAKT_CODE = "INT";
	public static final String OPTIMALCONDITIONST_CODE = "OPT";
}
