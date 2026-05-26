import os
import glob
import re

for df in glob.glob('BullyCars/*/Dockerfile'):
    with open(df, 'r') as f:
        content = f.read()
    
    # Extract EXPOSE port
    m = re.search(r'EXPOSE\s+(\d+)', content)
    port = m.group(1) if m else '8080'
    
    new_content = f'''FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE {port}
ENTRYPOINT ["java", "-jar", "app.jar"]
'''
    with open(df, 'w') as f:
        f.write(new_content)
        
print("Updated all Dockerfiles to multi-stage builds.")
