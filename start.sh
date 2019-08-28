#!/bin/bash
echo "Start Gestão de Processos"
docker-compose up --detach
mvn clean install -DskipTests
mvn spring-boot:run
