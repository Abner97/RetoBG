# RetoBG con JWT

Me tome el tiempo de mejorar la seguridad de la API con JWT.
*Solo se puede usar con un software de API testing como Postman *

*La copia de datos de la BD modificada para funcionar con JWT está en la carpeta /resources de esta branch. *

En el archivo application.properties que se encuentra en la carpeta /resources debe cambiar las credenciales por las credenciales de su BD:


    spring.datasource.url=jdbc:mysql://localhost:3306/db_bg?useSSL=false&serverTimezone=America/Panama
    spring.datasource.username=bg
    spring.datasource.password=retobg2020
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    logging.level.org.hibernate.SQL=debug


spring.datasource.username=bg -> usuario de la BD
spring.datasource.password=retobg2020 -> password de la BD

Para hacer login deber acceder a:

# Login
### POST
#### localhost:8080/auth/login

  Hay 2 usuarios registrados en la BD con los cuales puede acceder por medio de postman con esto en el Body (en formato JSON):
  
  ```json
    {
    "username":"sudonym",
    "password":"retobg2020"
    }
```
  
  ```json
    {
    "username":"admin",
    "password":"retobg2020"
    }
```

Esto devolvera un token JWT en el siguiente formato:

  ```json
   {

		"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdWRvbnltIiwiaWF0IjoxNjA2MzgyNDIwLCJleHAiOjE2MDY0MTg0MjB9.qc3D5HfbWkdQyqy_ULD6cZ8WA9gBrVgSKtbVYoQvkCpU045hUtnFrh6PiR0Hl4RoO6sDek68Qygx4UOwmXidNw",
		"bearer": "Bearer",
		"username": "sudonym",
		"authorities": [
			{
				"authority": "ROLE_USER"
			}
		]

}
```

# Registro

Para registar un nuevo usuario administrador debe usar el siguiente endpoint:
###  POST
#### localhost:8080/auth/new

El body debe tener el siguiente formato:

  ```json
  {

	"name":"Abraham",
	"username":"sudonym",
	"email":"abraham.497@hotmail.com",
	"password":"retobg2020",
	"roles":["user"]

}
```

Si desea darle un rol de administrador solo debe cambiar  la propiedad:
```json
	{
	"roles":["admin"]
	}
```

# CRUD
Para todas las operaciones necesita tener el token **JWT** :

En el **Header** de la petición debe ir su token JWT de esta forma:

    Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdWRvbnltIiwiaWF0IjoxNjA2MzgyNDIwLCJleHAiOjE2MDY0MTg0MjB9.qc3D5HfbWkdQyqy_ULD6cZ8WA9gBrVgSKtbVYoQvkCpU045hUtnFrh6PiR0Hl4RoO6sDek68Qygx4UOwmXidNw

Ejemplo en postman
![enter image description here](https://i.ibb.co/rQzC0sX/Screenshot-from-2020-11-26-04-54-52.png)


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
