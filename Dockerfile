FROM eclipse-temurin:20-jdk

COPY out/production/javaprojet_semestre5/ ./classes



ENTRYPOINT ["java", "-cp", "classes", "fr.itii.main"]
