CREATE TABLE IF NOT EXISTS appuser (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    isDeleted BOOLEAN NOT NULL,
    bio VARCHAR(255)
);
