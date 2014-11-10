#ByteMii :game_die:
##TPE Programacion Orientada a Objetos 2014 
###ITBA

> **ACLARACION: _Este programa requiere tener instaldo Java 8 o superior._**

![ByteMii Logo](/src/main/resources/img/logo.png)

ByteMii :game_die: es un emulador de juegos arcade.

El emulador consta de:
* Un CPU de las siguientes caracteristicas:
	* 16 registros de 8 bits llamados Vx, siendo x = 0x0, 0x1, ..., 0xF
	* Registro I de 16 bits utilizado para almacenar direcciones de memoria RAM
	* Un Delay Timer usado para sincronizar los eventos de los juegos.
	* Un Sound Timer usado para efectos de sonidos
* Una memoria RAM de 6K x 16
* Un Display de 64 * 32 pixeles bicromatico
* Una placa de sonido

###USO

1. Navegar por consola hasta la carpeta del Programa (tanto en Windows como en UNIX)
2. Si se tiene Gradle 2.1 instaldo en el sistema se pueden correr directamente los comandos listados debajo y saltear el punto 3).
3. En caso de no tener instalado Gradle 2.1, se debe reemplazar el "_gradle_" de los comandos listados debajo por "_./gradlew_" en UNIX o _"gradlew.bat"_ en Windows. Ej: para crear el .jar del programa en linux correr: `./gradlew jar`; y en windows: `"gradlew.bat" jar`

####------ ECLIPSE ------
- Para poder abrir el programa con Eclipse, correr por consola: `gradle eclipse` que crea los archivos necesarios para abrir el proyecto con el Eclipse.

- Para borrar los archivos previamente creados: `gradle cleanEclipse`.

####------ IDEA ------
- Para poder abrir el programa con el Intellij Idea, correr por consola: `gradle eclipse` que crea los archivos necesarios para abrir el proyecto con el Idea.

- Para borrar los archivos previamente creados:

```
gradle cleanIdea
gradle cleanIdeaWorkspace
```

####------ CONSOLA ------
- Para abrir el programa por consola correr:

Para solo compilar: `gradle build`.

Para compilar y ejecutar: `gradle run`.

####------ JAR ------
- Para generar el archivo .jar correr por consola: `gradle jar`.

El archivo creado se encuentra en _/build/lib/ByteMii-X.X.jar_

####------ INSTALACION ------
- Para generar los archivos necesarios del programa y los ejecutables pertinentes al Sistema Operativo correr: `gradle install`.

Esto compila los archivos, genera el .jar y permite correr el programa mediante un ejecutable (segun S.O.) ubicado en _/build/install/bin/_ .