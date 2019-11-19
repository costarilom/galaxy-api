Sistema para la prediccion del clima en una galaxia

	Repositorio del código fuente:	https://github.com/costarilom/galaxy-api.git


	Ejecución en un Host público
		* https://tiempo-api.herokuapp.com/galaxy/periods
			Retorna las condiciones climáticas de los 10 años
			
		* https://tiempo-api.herokuapp.com/galaxy/weather/day/0
			Retorna la condición climática para el día consultado.
			
		* https://tiempo-api.herokuapp.com/galaxy/periods/year/2019
			Retorna las condiciones climáticas del año consultado
			
		* https://tiempo-api.herokuapp.com/galaxy/periods/code/RAI
			Retorna la cantidad de días para el tipo de clima consultado. Siendo los códigos de clima
				-	DRO = Sequia
				-	RAI = Lluvia
				-	INT =  Pico de intensidad
				-	OPT = Condiciones optimas

	
		* https://tiempo-api.herokuapp.com/galaxy/clean
			Limpia la migracion realizada (La misma se ejecuta nuevamene a los 5 segundos)