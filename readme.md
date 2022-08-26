# setup postgressql


```postgresql
CREATE ROLE potato WITH LOGIN PASSWORD '1234';

create database user_db;

GRANT ALL PRIVILEGES ON DATABASE user_db TO potato;

```