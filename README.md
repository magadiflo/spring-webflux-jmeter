# Pruebas de rendimiento con JMeter

En este proyecto, veremos un conjunto de ejemplos de como realizar pruebas de rendimiento usando `JMeter` sobre nuestra
aplicación desarrollada con `Spring Boot WebFlux`. Aunque también habría podido usar un proyecto
`Spring Boot Web (Servlet)`, pero para variar quise usar un proyecto reactivo.

**Fuente**

- [Load Performance Testing with Apache JMeter - medium](https://medium.com/@truongbui95/load-performance-testing-with-apache-jmeter-e20b41d5a3f2)
- [JMeter tutorial](https://howtodoinjava.com/java/library/jmeter-beginners-tutorial/)

---

## Iniciar aplicación

Esta aplicación desarrollada con `Spring Boot WebFlux` está lista para ser levantada, simplemente debemos seguir estos
pasos:

1. Levantar el contenedor de la base de datos de `postgresql` con el comando `docker compose up -d`.
2. Levantar la aplicación. Cada vez que levantemos la aplicación, la base de datos se va a poblar con los mismos datos.

Para el desarrollo de las pruebas con `JMeter` trabajaremos con los endpoints del controlador `AuthorController`.

---

## [Apache JMeter](https://jmeter.apache.org/index.html)

La aplicación `Apache JMeter` es un software de código abierto, una aplicación 100% Java diseñada para realizar pruebas
de carga del comportamiento funcional y medir el rendimiento. Originalmente, se diseñó para probar aplicaciones web,
pero desde entonces se ha expandido a otras funciones de prueba.

## ¿Qué puedo hacer con él?

Apache JMeter puede utilizarse para probar el rendimiento de recursos estáticos y dinámicos, aplicaciones web dinámicas.
Puede utilizarse para simular una carga pesada en un servidor, grupo de servidores, red u objeto para probar su
resistencia o para analizar el rendimiento general bajo diferentes tipos de carga.

Las características de `Apache JMeter` incluyen:

- Capacidad para cargar y probar el rendimiento de muchos tipos diferentes de aplicaciones/servidores/protocolos:
    - Web: HTTP, HTTPS (Java, NodeJS, PHP, ASP.NET, …)
    - Servicios web SOAP/REST
    - FTP
    - Base de datos a través de JDBC
    - LDAP
    - Middleware orientado a mensajes (MOM) a través de JMS
    - Correo: SMTP(S), POP3(S) e IMAP(S)
    - Comandos nativos o scripts de shell
    - TCP
    - Objetos Java

Hoy, mostraremos un método simple de prueba de rendimiento de carga que utiliza `Apache JMeter`. Como aplicación de
escritorio de código abierto basada en Java, `Apache JMeter` ofrece una amplia gama de beneficios, que incluyen:

- Permite realizar pruebas de carga y rendimiento de varias aplicaciones, servidores y tipos de protocolos. Esta
  flexibilidad permite a los desarrolladores evaluar el rendimiento de diversos sistemas de manera efectiva.


- Ayuda a determinar la cantidad máxima de usuarios simultáneos que un sitio web o una aplicación pueden manejar. Esta
  información es crucial para identificar posibles problemas de escalabilidad y optimizar el rendimiento.


- Proporciona informes de rendimiento en múltiples formatos. Estos informes ofrecen información y métricas valiosas, lo
  que permite a los evaluadores y desarrolladores analizar e interpretar los resultados de las pruebas de manera
  efectiva.

## Instalación y ejecución de Apache JMeter

Para comenzar a utilizar `Apache JMeter`, siga estos pasos:

1. Visite el sitio web oficial de [Apache JMeter](https://jmeter.apache.org/download_jmeter.cgi) y descargue la última
   versión de `JMeter` como archivo `zip`.
2. Extraiga el contenido del archivo `zip` descargado en la ubicación deseada de su computadora.
3. Ingrese dentro de la carpeta `/bin` del directorio JMeter extraído. Observe que tenemos el archivo `jmeter.bat`,
   el cual lo usaremos para ejecutar la aplicación de JMeter, dado que estamos en `Windows`.

   ![01.png](assets/01.png)

4. Podemos dar doble clic al archivo `jmeter.bat` para iniciar la aplicación de JMeter, o como en nuestro caso usar
   nuestra propia terminal.

      ````bash
      C:\apache-jmeter-5.6.3\bin
   
      $ jmeter.bat
      WARN StatusConsoleListener The use of package scanning to locate plugins is deprecated and will be removed in a future release
      WARN StatusConsoleListener The use of package scanning to locate plugins is deprecated and will be removed in a future release
      WARN StatusConsoleListener The use of package scanning to locate plugins is deprecated and will be removed in a future release
      WARN StatusConsoleListener The use of package scanning to locate plugins is deprecated and will be removed in a future release
      ================================================================================
      Don't use GUI mode for load testing !, only for Test creation and Test debugging.
      For load testing, use CLI Mode (was NON GUI):
         jmeter -n -t [jmx file] -l [results file] -e -o [Path to web report folder]
      & increase Java Heap to meet your test requirements:
         Modify current env variable HEAP="-Xms1g -Xmx1g -XX:MaxMetaspaceSize=256m" in the jmeter batch file
      Check : https://jmeter.apache.org/usermanual/best-practices.html
      ================================================================================
      ````

Si la instalación fue exitosa, debería ver la siguiente pantalla.

![02.png](assets/02.png)

