#ByteMii
##TPE 2014 - Programacion Orientada a Objetos - ITBA

ACLARACION: Este programa requiere tener instaldo Java 8 o superior.

ByteMii es un emulador de Juegos programados con el lenguaje Chip8.

###USO
Estos comandos suponen que Gradle 2.1 esta instalado en el sistema.
En caso de no terlo instalado, reemplazar "gradle" por "./gradlew" en Linux o "gradlew.bat" en Windows, en los comandos listados debajo... (Aclaracion, para correr los comandos siempre se debe estar en la carpeta del programa, tanto en Linux como en Windows)

####------ ECLIPSE ------
Para abrir el programa con Eclipse correr por consola:
> gradle eclipse // Crea los archivos necesarios para abrir el proyecto con el Eclipse
Para borrar los archivos previamente creados:
> gradle cleanEclipse

####------ IDEA ------
Para abrir el programa con Idea correr por consola:
> gradle eclipse // Crea los archivos necesarios para abrir el proyecto con el Idea
Para borrar los archivos previamente creados:
> gradle cleanIdea
> gradle cleanIdeaWorkspace

####------ CONSOLA ------
Para abrir el programa por consola correr:
> gradle build // Compila
O
> gradle run // Compila y ejecuta

####------ JAR ------
Para generar el archivo .Jar correr por consola:
> gradle jar
El archivo creado se encuentra en build/lib/ByteMii-X.X.jar

####------ INSTALACION ------
Para generar los archivos necesarios del programa y los ejecutables pertinentes al Sistema Operativo correr:
-> gradle install
Esto compila los archivos, genera el .Jar y permite correr el programa mediante un ejecutable (segun S.O.) ubicado en build/install/bin/