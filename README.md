﻿# GitHub User Activity Tracker
 https://roadmap.sh/projects/github-user-activity

Este proyecto permite hacer un seguimiento de la actividad de un usuario en GitHub utilizando la API pública de GitHub. Puedes obtener detalles sobre las actividades recientes de un usuario, como sus commits, repositorios y otros eventos.

## Requisitos

- **Java 21 o superior**: Este proyecto está desarrollado en Java.
- **Dependencia de Gson**: Utiliza la biblioteca Gson para manejar el formato JSON recibido desde la API de GitHub.

La dependencia de Gson se incluye en el archivo `pom.xml` de Maven:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```
## Instalacion
1. Clona el repositorio o descarga los archivos del proyecto.
```bash
    git clone https://github.com/AndresGuillermo07/github-user-activity.git
```
2. Asegúrate de tener Maven instalado en tu sistema. Si no lo tienes, puedes descargarlo desde [aquí.](https://maven.apache.org/download.cgi)
3. Navega al directorio del proyecto y ejecuta el siguiente comando para compilar el proyecto:
```bash
    mvn clean install
```
4. El proyecto está listo para ser ejecutado.

## Uso
Para ejecutar el proyecto, simplemente pasa el nombre de usuario de GitHub como argumento en la línea de comandos:
```bash
    java -jar github-user-activity.jar <usuario_de_github>
```
### Ejemplo
```bash
    java -jar github-user-activity.jar octocat
```
Este comando hará una consulta a la API de GitHub para obtener la actividad del usuario octocat.

## Funcionalidades
- Seguimiento de actividad de usuario: El proyecto obtiene eventos de GitHub (como commits, repositorios, issues, etc.) asociados al usuario.
- Interacción con la API de GitHub: Realiza solicitudes a la API pública de GitHub y procesa las respuestas en formato JSON.
- Formato de salida: La información obtenida se presenta en la consola, permitiendo visualizar la actividad del usuario.


