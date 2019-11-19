Sistema para la prediccion del clima en una galaxia

	Repositorio del c�digo fuente:	https://github.com/costarilom/galaxy-api.git


	Ejecuci�n en un Host p�blico
		* https://tiempo-api.herokuapp.com/galaxy/periods
			Retorna las condiciones clim�ticas de los 10 a�os
			
		* https://tiempo-api.herokuapp.com/galaxy/weather/day/0
			Retorna la condici�n clim�tica para el d�a consultado.
			
		* https://tiempo-api.herokuapp.com/galaxy/periods/year/2019
			Retorna las condiciones clim�ticas del a�o consultado
			
		* https://tiempo-api.herokuapp.com/galaxy/periods/code/RAI
			Retorna la cantidad de d�as para el tipo de clima consultado. Siendo los c�digos de clima
				-	DRO = Sequia
				-	RAI = Lluvia
				-	INT =  Pico de intensidad
				-	OPT = Condiciones optimas

	
		* https://tiempo-api.herokuapp.com/galaxy/clean
			Limpia la migracion realizada (La misma se ejecuta nuevamene a los 5 segundos)