# SessionHijacking-Server
## Spring server từ project root
- Cài java 11 + docker desktop
- Dùng docker compose ``docker-compose up``
- Restore file dump sql bằng cách chạy 
    - ``docker cp ./src/main/sql/pg_dump.sql postgres_container:/tmp/pg_dump.sql``
    - ``docker exec -it postgres_container /bin/sh``
    - ``psql -U postgres < /tmp/pg_dump.sql``
- chạy file start.bat
