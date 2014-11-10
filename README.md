#ByteMii
##TPE Programacion Orientada a Objetos 2014 
###ITBA

> **ACLARACION: _Este programa requiere tener instaldo Java 8 o superior._**

![ByteMii Logo](/src/main/resources/img/logo.png)

ByteMii :sparkles: es un emulador de Juegos programados con el lenguaje Chip8.

###USO
Estos comandos suponen que Gradle 2.1 esta instalado en el sistema.
En caso de no terlo instalado, reemplazar _gradle_ por _"./gradlew"_ en Linux o _"gradlew.bat"_ en Windows, en los comandos listados debajo... (Aclaracion, para correr los comandos siempre se debe estar en la carpeta del programa, tanto en Linux como en Windows)

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

Para solo compilar: `gradle build`

Para compilar y ejecutar: `gradle run`

####------ JAR ------
- Para generar el archivo .jar correr por consola: `gradle jar`

El archivo creado se encuentra en _/build/lib/ByteMii-X.X.jar_

####------ INSTALACION ------
- Para generar los archivos necesarios del programa y los ejecutables pertinentes al Sistema Operativo correr: `gradle install`

Esto compila los archivos, genera el .jar y permite correr el programa mediante un ejecutable (segun S.O.) ubicado en _/build/install/bin/_