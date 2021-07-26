CREATE TABLE IF NOT EXISTS polls (
    id SERIAL PRIMARY KEY ,
    title VARCHAR(100) ,
    start_date DATE ,
    finish_date DATE ,
    description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS questions (
    id SERIAL PRIMARY KEY ,
    type VARCHAR (40) ,
    text VARCHAR (1000),
    poll_id INTEGER REFERENCES polls (id)
);

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR (15) PRIMARY KEY ,
    password VARCHAR (100),
    enabled SMALLINT
);

CREATE TABLE IF NOT EXISTS authorities (
    username varchar(15),
    authority varchar(25),
    FOREIGN KEY (username) references users(username)
);

CREATE TABLE IF NOT EXISTS answers (
    id SERIAL PRIMARY KEY ,
    username VARCHAR (15) NOT NULL,
    question_id INTEGER REFERENCES questions (id) NOT NULL,
    text VARCHAR (1000) NOT NULL
);

INSERT INTO users (username, password, enabled)
        VALUES
            ('vlad', '{noop}vlad', 1),
            ('elena', '{noop}elena', 1);

INSERT INTO authorities (username, authority)
        VALUES
            ('vlad', 'ROLE_ADMIN'),
            ('elena', 'ROLE_USER');



