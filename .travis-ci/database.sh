#bin/bash

mysql -e 'CREATE DATABASE vendor_lion;'
mysql vendor_lion < src/main/resources/schema.sql
mysql vendor_lion < src/main/resources/data.sql
