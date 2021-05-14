## Exp-Store
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7abc97e87ae94e14bb3d85f6288e0c0e)](https://app.codacy.com/gh/DessertFox17/Exp-Store?utm_source=github.com&utm_medium=referral&utm_content=DessertFox17/Exp-Store&utm_campaign=Badge_Grade_Settings)

#  Clothesstore LATAM

### 2021

## 👥 Juan Esteban Arango Amaya - jblackheart@gmail.com

Backend con conexión a base de datos para buscar, registrar, comprar productos.

## Diseño de base de datos

Es una base de datos relaconas basada en PostgreSQL, diseñada con una metodología 
de 9 pasos para analizar, modelar y construir una base de datos, en la ruta del 
proyecto se puede encontrar la carpeta DatabaseDesing, ver el archivo Clothesstore 
LATAM Database Design.pdf

## Puesta a punto 

El proyecto puede ser clonado a través de Git, a través del comando:

<code>
git clone https://github.com/DessertFox17/Exp-Store.git
<code>

Las dependencias necesarias para el proyecto son:

<code>
  //Spring Boot Web
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'junit:junit:4.13.1'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
<code>
	//Spring Data
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
<code>
	//Spring Boot Security
	implementation 'org.springframework.boot:spring-boot-starter-security'
<code>
	//JWT
	implementation 'io.jsonwebtoken:jjwt:0.9.1'
<code>
	//PostgreSQL
	runtimeOnly 'org.postgresql:postgresql'
<code>
	//Lombok
	compileOnly 'org.projectlombok:lombok:1.18.18'
	annotationProcessor 'org.projectlombok:lombok:1.18.18'
<code>
	//ModelMapper
	implementation 'org.modelmapper:modelmapper:2.4.0'
<code>
	//Spring Boot Mail
	implementation 'org.springframework.boot:spring-boot-starter-mail'
<code>
	//Swagger
	implementation 'io.springfox:springfox-swagger2:2.9.2'
	implementation 'io.springfox:springfox-swagger-ui:2.9.2'
<code>
