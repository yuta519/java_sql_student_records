#!/bin/bash
HOSTNAME="localhost"
PORT="3306"
USERNAME="root"
PASSWORD=""
DBNAME="student_records"
TABLENAME="students"

createDBStatement="create database IF NOT EXISTS ${DBNAME}"
mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} -e "${createDBStatement}"

# Create a `students` Table
createTBLStatement="
    create table IF NOT EXISTS ${TABLENAME} (
        id int(11) NOT NULL PRIMARY KEY,
        student_id int(11) default 0,
        name varchar(20),
        email varchar(320) UNIQUE,
        student_roll_number int(11) UNIQUE
    )"
mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${createTBLStatement}"

# Insert student data
insertStatement="
    insert into ${TABLENAME} values(
        1,
        1,
        'Yuta Kawamura',
        'ytkwmr18@gmail.com',
        1
    )"
mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${insertStatement}"

# Query database
selectStatement="select * from ${TABLENAME}"
mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${selectStatement}"

# Update data
# update_sql="update ${TABLENAME} set id=3"
# mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${update_sql}"
# mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${selectSQL}"

# # Delete data
# deleteSQL="delete from ${TABLENAME}"
# mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${deleteSQL}"
# mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${selectSQL}"
