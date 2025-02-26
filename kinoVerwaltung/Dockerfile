# Basis-Image für Java
FROM openjdk:21-jdk-slim

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere die JAR-Datei
COPY target/kinoVerwaltung-0.0.1-SNAPSHOT.jar app.jar


# Exponiere den Port für das Backend
EXPOSE 8080

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "app.jar"]
