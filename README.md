# Pruebas de rendimiento con JMeter

En este proyecto, veremos un conjunto de ejemplos de como realizar pruebas de rendimiento usando `JMeter` sobre nuestra
aplicación desarrollada con `Spring Boot WebFlux`. Aunque también habría podido usar un proyecto
`Spring Boot Web (Servlet)`, pero para variar quise usar un proyecto reactivo.

---

## Iniciar aplicación

Esta aplicación desarrollada con `Spring Boot WebFlux` está lista para ser levantada, simplemente debemos seguir estos
pasos:

1. Levantar el contenedor de la base de datos de `postgresql` con el comando `docker compose up -d`.
2. Levantar la aplicación. Cada vez que levantemos la aplicación, la base de datos se va a poblar con los mismos datos.

Para el desarrollo de las pruebas con `JMeter` trabajaremos con los endpoints del controlador `AuthorController`.

