USE siem;

CREATE TABLE verification_token (
    id INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11),
    token VARCHAR(255) NOT NULL,
    expiry_date DATETIME,
    PRIMARY KEY(id), 
    FOREIGN KEY (user_id) REFERENCES users (id)
);
