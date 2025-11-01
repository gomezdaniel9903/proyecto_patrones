PROYECTO PATRONES JAVA
===================================

Implementación de Patrones de Arquitectura.

-----------------------------------
CONFIGURACIÓN DEL ENTORNO
-----------------------------------

REQUISITOS:
- Java 17 o superior
- Maven 3.8+
- MySQL 8.0+
- IntelliJ IDEA (opcional pero recomendado)

1. Crear la base de datos con el script SQL suministrado.
2. Actualizar el usuario y contraseña de la base de datos en el archivo application.properties.
3. Compilar e iniciar la aplicación con el siguiente comando:
   mvn spring-boot:run

-----------------------------------
MANUAL DE USO
-----------------------------------

Una vez inicializado el proyecto, este correrá en el puerto 8080 en el navegador.

    http://localhost:8080

En la interfaz del usuario:
- Se debe cargar un archivo con extensión .fasta o .txt.
- Seleccionar mediante los checkbox las opciones de análisis que se desean ejecutar.
- El alineamiento de secuencias se realizará utilizando los dos primeros registros del archivo.
- Es posible seleccionar varios análisis al mismo tiempo.

Al finalizar el análisis:
- Aparecerán los botones para ver el resultado en pantalla o descargar un archivo .csv con los resultados.
- Finalmente, se mostrará un botón para reiniciar y realizar un nuevo análisis desde cero.

-----------------------------------
