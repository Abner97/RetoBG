# RetoBG 



*La copia de datos de la BD está en la carpeta /resources de esta branch. *


# CRUD

## Leer todos los usuarios
###  Get
#### localhost:8080/api/v1/users


## Leer un solo usuario por id
###  Get
#### localhost:8080/api/v1/users/{id}
Ejemplo:
	

    localhost:8080/api/v1/users/27

## Crear usuario común y corriente 

###  POST
#### localhost:8080/api/v1/users

Body:
```json
{
	"name": "Brock Sutton",
	"username": "elit",
	"email": "brocksutton@zidant.com",
	"address": {
		"street": "Emmons Avenue",
		"suite": "Apt. 185",
		"city": "Gibbsville",
		"zipcode": "5159-1823",
		"geo": {
			"lat": -79.724252,
			"lng": -13.070713
		}
	},
	"phone": "+1 (811) 471-3289",
	"website": "winifred.com",
	"company": {
		"name": "GRACKER",
		"catchPhrase": "anim laborum",
		"bs": "consectetur voluptate"
	}
}
```
## Crear muchos usuarios por medio de una lista
###  POST
#### localhost:8080/api/v1/users/list
	Body:
```json
[
	{
		"name": "Brock Sutton",
		"username": "elit",
		"email": "brocksutton@zidant.com",
		"address": {
			"street": "Emmons Avenue",
			"suite": "Apt. 185",
			"city": "Gibbsville",
			"zipcode": "5159-1823",
			"geo": {
				"lat": -79.724252,
				"lng": -13.070713
			}
		},
		"phone": "+1 (811) 471-3289",
		"website": "winifred.com",
		"company": {
			"name": "GRACKER",
			"catchPhrase": "anim laborum",
			"bs": "consectetur voluptate"
		}
	},
	{
		"name": "Sandy Sykes",
		"username": "velit",
		"email": "sandysykes@gracker.com",
		"address": {
			"street": "Williams Place",
			"suite": "Apt. 125",
			"city": "Farmington",
			"zipcode": "6852-6582",
			"geo": {
				"lat": 66.441962,
				"lng": 89.994969
			}
		},
		"phone": "+1 (962) 547-3412",
		"website": "patterson.com",
		"company": {
			"name": "FUELWORKS",
			"catchPhrase": "dolore voluptate",
			"bs": "labore veniam"
		}
	},
]

```
## Actualizar un usuario
###  PUT
#### localhost:8080/api/v1/users/{id}
Ejemplo:
	

    localhost:8080/api/v1/users/1

Body:
```json
{
	"name": "Brock Vega",
	"username": "elit",
	"email": "brockvega@zidant.com",
	"address": {
		"street": "Emmons Avenue",
		"suite": "Apt. 185",
		"city": "Gibbsville",
		"zipcode": "5159-1823",
		"geo": {
			"lat": -79.724252,
			"lng": -13.070713
		}
	},
	"phone": "+1 (811) 471-343434",
	"website": "winifred.com",
	"company": {
		"name": "GRACKER",
		"catchPhrase": "anim laborum",
		"bs": "consectetur voluptate"
	}
}
```
## Borrar un usuario
###  DELETE
#### localhost:8080/api/v1/users/{id}


# GRACIAS POR LA OPORTUNIDAD!
![Thanks](https://i.pinimg.com/originals/e4/26/70/e426702edf874b181aced1e2fa5c6cde.gif)
