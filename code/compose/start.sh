#!/usr/bin/env bash
BOLD=$(tput bold)
NORMAL=$(tput sgr0)

# shut down if already started, ignore output
./down.sh > /dev/null 2>&1

docker compose up --detach
docker compose logs postgres


echo -e "\n\n\n# ====== \033[1madd the following to src/main/resources/application.properties\033[0m ======"
cat << EOF
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=demo
quarkus.datasource.password=demo
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/demo
%prod.quarkus.datasource.jdbc.url=jdbc:postgresql://postgres:5432/demo

quarkus.hibernate-orm.database.generation=drop-and-create
%prod.quarkus.hibernate-orm.database.generation=none
quarkus.hibernate-orm.database.generation.halt-on-error=false
%dev.quarkus.hibernate-orm.sql-load-script=db/import.sql
quarkus.hibernate-orm.physical-naming-strategy=org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
quarkus.datasource.devservices.enabled=false
quarkus.hibernate-orm.log.sql=true
quarkus.http.access-log.enabled=true
# ========================================================================


EOF
echo "starting the log output now, press ^C and ./down.sh to stop..."
sleep 2 # let user read the output
docker compose logs -f postgres
