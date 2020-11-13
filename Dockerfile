FROM mysql:8.0

COPY src/main/resources/dump.sql /docker-entrypoint-initdb.d/tutor.sql
