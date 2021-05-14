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


### Products

Contiene endpoints para:

- Crear productos
- Asignar imágenes a productos
- Filtro inteligente para autocompletar búsquedas
- Filtro dinámico con opciones de búesqueda por precio ASC, DESC y por más buscados
- Búesqueda de detalles específicos de un producto.

### Purchases

Contiene endpoints para: 

- Buscar las compras de un usuario
- Visualizar el carrito de un usuario
- Actualizar el estado de una compra
- Realizar nuevas compras

### Users

Contiene endpoints para: 

- Visualizar los datos de usuario
- Registrar nuevos usuarios

### Authorization

Contiene un endpoint de loggin que retorna el JWT de acceso.






  
  
  
  
