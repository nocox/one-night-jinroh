CREATE ROLE yuru_jinrou_dev_rw WITH LOGIN PASSWORD 'yuru_jinrou_database_passwd';
GRANT ALL PRIVILEGES ON DATABASE yuru_jinrou_db TO yuru_jinrou_dev_rw;