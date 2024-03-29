CREATE SCHEMA IF NOT EXISTS siem;

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT ,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    email VARCHAR(50),
    password VARCHAR(255),
    admin BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS alerts (
    id INT NOT NULL AUTO_INCREMENT ,
    event_type VARCHAR(20),
    event_name VARCHAR(20),
    date TIMESTAMP,
    priority INT(1),
    custom_data VARCHAR(2048),
    PRIMARY KEY (id)
);