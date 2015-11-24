# ![ByteMii Logo](/src/main/resources/img/logo.png)

> **ACLARACION: _Este programa requiere tener instalado Java 8 o superior con JavaFX8._**

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

> **Integrantes: _Gonzalo Ibars Ingman y Juan Moreno._**

# Index
1. Usage
2. Commands
	- Eclipse
	- IDEA
	- Terminal
	- JAR
3. Notes

# Usage

1. Navegar por consola hasta la carpeta del Programa (tanto en Windows como en UNIX).
2. Se requiere tener Gradle 2.1 instalado en el sistema se pueden correr directamente los comandos listados debajo y saltear el punto 3).
3. En caso de no tener instalado Gradle 2.1, se debe reemplazar el "_gradle_" de los comandos listados debajo por "_./gradlew_" en UNIX o "_gradlew_" en Windows. Ej: para crear el .jar del programa en UNIX correr: `./gradlew jar`; y en windows: `gradlew jar` (siempre estando en la carpeta del Programa).

# Commands

## Eclipse
- Para poder abrir el programa con Eclipse, correr por consola: `gradle eclipse` que crea los archivos necesarios para abrir el proyecto con el Eclipse.

- Para borrar los archivos previamente creados: `gradle cleanEclipse`.

## IDEA
- Para poder abrir el programa con el Intellij Idea, correr por consola: `gradle eclipse` que crea los archivos necesarios para abrir el proyecto con el Idea.

- Para borrar los archivos previamente creados:

```
gradle cleanIdea
gradle cleanIdeaWorkspace
```

## Terminal
- Para abrir el programa por consola correr:

Para solo compilar: `gradle build`.

Para compilar y ejecutar: `gradle run`.

## JAR
- Para generar el archivo .jar correr por consola: `gradle jar`.

El archivo creado se encuentra en _/build/lib/ByteMii-X.X.jar_

# Notes

- Para realizar una implementación más precisa de los registros del CPU original y al mismo tiempo facilitar la programación del Emulador debido a las restricciones de Java (la ausencia de tipos unsigned), se optó por utilizar el módulo (%) para mantener los valores dentro del rango original.

- En vez de utilizar la implementación de Stack del propio Java, se creó una propia implementación del Stack que reside dentro de la memoria RAM, ya que representa mejor a la máquina original. Por la misma razón, se implementó el mapa de memoria para el CPU, para dar una representación mas certera del Hardware.

- Las clases Emulator y Main son aquellas que se encargan de mediar entre el back-end y el front-end.

- La implementación del programa tiene el front-end y el back-end bien separados para facilitar el cambio de la implementacion de la interfaz grafica sin tener que modificar el back.

- Se creó una clase Bitwise con métodos para la realización de operaciones (bitwise) entre elementos.
