FROM mysql:8

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=realdooh

COPY script/init.sql /docker-entrypoint-initdb.d/
