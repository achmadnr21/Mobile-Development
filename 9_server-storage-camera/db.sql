CREATE DATABASE simple_file_storage;

CREATE TABLE simple_file_storage.files (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    file_name TEXT NOT NULL,
    path TEXT NOT NULL
);