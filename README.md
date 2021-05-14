## Exp-Store
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7abc97e87ae94e14bb3d85f6288e0c0e)](https://app.codacy.com/gh/DessertFox17/Exp-Store?utm_source=github.com&utm_medium=referral&utm_content=DessertFox17/Exp-Store&utm_campaign=Badge_Grade_Settings)

#  Clothesstore LATAM

---

### 2021

## 👥 Juan Esteban Arango Amaya - jblackheart@gmail.com

Backend con conexión a base de datos para buscar, registrar, comprar productos.

---

## Diseño de base de datos

Es una base de datos relaconas basada en PostgreSQL, diseñada con una metodología 
de 9 pasos para analizar, modelar y construir una base de datos, en el siguiente 
enlace se encuentra la metodología de diseño descrita y posteriormente sus anexos.

[Clothesstore LATAM Database Design.pdf](https://github.com/DessertFox17/Exp-Store/files/6480539/Clothesstore.LATAM.Database.Design.pdf)

### Anexos


- [E - R Diagram.pdf](https://github.com/DessertFox17/Exp-Store/files/6480542/E.-.R.Diagram.pdf)

- [Conceptual Model.pdf](https://github.com/DessertFox17/Exp-Store/files/6480547/Conceptual.Model.pdf)

- [Logic Model.pdf](https://github.com/DessertFox17/Exp-Store/files/6480552/Logic.Model.pdf)

- [Physical Model.pdf](https://github.com/DessertFox17/Exp-Store/files/6480560/Physical.Model.pdf)

- [Tables generation create.txt](https://github.com/DessertFox17/Exp-Store/files/6480561/Tables.generation.create.txt)

- [Data charge.txt](https://github.com/DessertFox17/Exp-Store/files/6480570/Data.charge.txt)


---

## Desarrollo

Este proyecto fue desarrollado en el IDE de JetBrains 

IntelliJ IDEA 2021.1.1 (Ultimate Edition)
Build #UI-211.7142.45

Java JDK 11 Open JDK

---

## Puesta a punto 

El proyecto puede ser clonado a través de Git, a través del comando:


```
git clone https://github.com/DessertFox17/Exp-Store.git
```


Las dependencias necesarias para el proyecto son:


```
	//Spring Boot Web
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'junit:junit:4.13.1'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	implementation 'org.springframework.boot:spring-boot-starter-validation'

	//Spring Data
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

	//Spring Boot Security
	implementation 'org.springframework.boot:spring-boot-starter-security'

	//JWT
	implementation 'io.jsonwebtoken:jjwt:0.9.1'

	//PostgreSQL
	runtimeOnly 'org.postgresql:postgresql'

	//Lombok
	compileOnly 'org.projectlombok:lombok:1.18.18'
	annotationProcessor 'org.projectlombok:lombok:1.18.18'

	//ModelMapper
	implementation 'org.modelmapper:modelmapper:2.4.0'

	//Spring Boot Mail
	implementation 'org.springframework.boot:spring-boot-starter-mail'
	//Swagger
	implementation 'io.springfox:springfox-swagger2:2.9.2'
	implementation 'io.springfox:springfox-swagger-ui:2.9.2'
  ```
  
  
  Una vez abierto el proyecto abrir el archivo build.gradle y verificar las dependencias
  
  
  
  ![buildgradle](https://user-images.githubusercontent.com/80864158/118308468-965fbd80-b4b1-11eb-9a09-43ccb9b95518.jpg)
  


  Posterior a ello recargar las dependencias y reconstruir el proyecto
  
  
  
  ![reloadgradle](https://user-images.githubusercontent.com/80864158/118309685-39650700-b4b3-11eb-8256-9db4bf002bff.jpg)
  
  
---

## Funcionalidades


### Productos

Contiene endpoints para:

- Crear productos
- Asignar imágenes a productos
- Filtro inteligente para autocompletar búsquedas
- Filtro dinámico con opciones de búesqueda por precio ASC, DESC y por más buscados
- Búesqueda de detalles específicos de un producto.

### Compras

Contiene endpoints para: 

- Buscar las compras de un usuario
- Visualizar el carrito de un usuario
- Actualizar el estado de una compra
- Realizar nuevas compras

### Usuarios

Contiene endpoints para: 

- Visualizar los datos de usuario
- Registrar nuevos usuarios

### Autorización

Contiene un endpoint de loggin que retorna el JWT de acceso.

### Documentación en Swagger

para mayor información sobre la API se puede seguir el siguiente link en donde se 
muestra la documentación de la API con Swagger.

http://expestore.herokuapp.com/store/swagger-ui.html

---

## Acceso

El acceso está definido de la siguiente manera:

### Público

- Registrar nuevos usuarios
- Filtro inteligente para autocompletar búsquedas
- Filtro dinámico con opciones de búesqueda por precio ASC, DESC y por más buscados
- Búesqueda de detalles específicos de un producto.

### Usuarios registrados

- Visualizar los datos de usuario
- Registrar nuevos usuarios
- Buscar las compras de un usuario
- Visualizar el carrito de un usuario
- Actualizar el estado de una compra
- Realizar nuevas compras

### Administradores o usuarios root

- Crear productos
- Asignar imágenes a productos

Estos accesos estarán delimitados por el role al que perteneza cada cuenta, al retornar el JWT
para el acceso a la API.

---

## Pruebas con Postman

A en el siguiente enlace se dejará la colección de Postman para sus pruebas con este software,
es necesario crear dos ambientes ya sea que se requiera probar de manera local o la API desplegada
en heroku:

- Variable de ambiente en Postman para API en Heroku

![heroku](https://user-images.githubusercontent.com/80864158/118323497-57883280-b4c6-11eb-8c45-6d01b66e5df9.jpg)

- Variable de ambiente en Postman para API en Local

![Local](https://user-images.githubusercontent.com/80864158/118323515-5ce57d00-b4c6-11eb-9a36-e28429a568dd.jpg)

La colección de Postman para los endpoint está en la siguinete ruta

[Clothesstore LATAM.postman_collection.zip](https://github.com/DessertFox17/Exp-Store/files/6481002/Clothesstore.LATAM.postman_collection.zip)

Cabe recordar que los enpoint tienen niveles de acceso según el rol del usuario, por ende para los endpoints 
de tipo público no es necesario utilizar el token, para los demás se debe hacer de la siguiente manera:

Se debe ir al endpoint de autenticación, ingresar el email y la contraseña para posteriormente recibir un token

![peticion](https://user-images.githubusercontent.com/80864158/118324546-d03bbe80-b4c7-11eb-908b-a313ffa49817.jpg)

Cabe recordar que algunos endpoint tienen nivel de acceso Admin o Root por ennde si se desean probar todos los endpoints 
se puede generar un token tipo user o uno tipo Admin o Root

- User

```
{
    "username": "catias@yopmail.com",
    "password": "holahola"
}
```

- Admin

```
{
    "username": "jblackheart@yopmail.com",
    "password": "holahola"
}
```

Posteriormente en la pestaña Authorization en Postman, se debe seleccionar el Type Bearer Token y en el campo
designado se debe ingresar el token recibido anteriormente.

![token](https://user-images.githubusercontent.com/80864158/118324907-4d673380-b4c8-11eb-8640-8e66dbf04ae4.jpg)

Ahora estás listo para iniciar las pruebas.

## Gracias
## Saludos



  
  
  
  
