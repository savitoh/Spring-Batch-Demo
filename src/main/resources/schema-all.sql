DROP TABLE IF EXISTS user;

CREATE TABLE user (
    user_id INT NOT NULL,
    name VARCHAR(50),
    send_at DATETIME DEFAULT NULL,
    job VARCHAR(50),
    PRIMARY KEY (user_id)
);