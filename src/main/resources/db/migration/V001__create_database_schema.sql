USE nearcode;

CREATE TABLE user(
                     id BIGINT NOT NULL AUTO_INCREMENT,
                     name VARCHAR(255) NOT NULL,
                     phone CHAR(9) NOT NULL,
                     email VARCHAR(255) NOT NULL,
                     create_time TIMESTAMP NOT NULL,
                     update_time TIMESTAMP NOT NULL,
                     version SMALLINT NOT NULL,
                     PRIMARY KEY (id)
);

CREATE TABLE car(
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    brand VARCHAR(255) NOT NULL,
                    model VARCHAR(255) NOT NULL,
                    license_plate CHAR(8) NOT NULL,
                    user_id BIGINT NOT NULL,
                    create_time TIMESTAMP NOT NULL,
                    update_time TIMESTAMP NOT NULL,
                    version SMALLINT NOT NULL,
                    PRIMARY KEY (id),
                    FOREIGN KEY (user_id) REFERENCES user (id)
);