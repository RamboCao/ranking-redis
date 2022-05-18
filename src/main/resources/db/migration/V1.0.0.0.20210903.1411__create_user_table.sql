CREATE TABLE user
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(200),
    create_id      INT,
    create_instant TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modify_id      INT,
    modify_instant TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

COMMIT;