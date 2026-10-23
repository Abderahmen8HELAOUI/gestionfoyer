# --- Étape 1 : Compilation ---
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et compiler le .jar
COPY src ./src
RUN mvn clean package -DskipTests

# --- Étape 2 : Exécution ---
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copier le .jar généré depuis l'étape 1
COPY --from=build /app/target/*.jar app.jar

# Exposer le port (Spring Boot utilise 8080 par défaut)
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]