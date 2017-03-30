DROP DATABASE IF EXISTS Tutor_Test;

CREATE DATABASE Tutor_Test;

USE Tutor_Test;

CREATE TABLE User (
  id INT NOT NULL AUTO_INCREMENT,
  role ENUM('STUDENT', 'TUTOR') NOT NULL,
  email VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL,
  name VARCHAR(20) NOT NULL,
  surname VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Quiz (
  id INT NOT NULL AUTO_INCREMENT,
  owner_id INT NOT NULL,
  name VARCHAR(20) NOT NULL UNIQUE,
  active BOOLEAN NOT NULL DEFAULT 0,
  FOREIGN KEY (owner_id) REFERENCES `User`(id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);

CREATE TABLE Result (
  id INT NOT NULL AUTO_INCREMENT,
  quiz_id INT NOT NULL,
  user_id INT NOT NULL,
  mark DECIMAL(5, 2) NOT NULL,
  creation_datetime TIMESTAMP,
  FOREIGN KEY(quiz_id) REFERENCES Quiz(id) ON DELETE CASCADE,
  FOREIGN KEY(user_id) REFERENCES `User`(id) ON DELETE CASCADE,
  PRIMARY KEY(id)
);

CREATE TABLE Question (
  id INT NOT NULL AUTO_INCREMENT,
  quiz_id INT NOT NULL,
  `text` TEXT NOT NULL,
  FOREIGN KEY (quiz_id) REFERENCES Quiz(id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);

CREATE TABLE Answer (
  id INT NOT NULL AUTO_INCREMENT,
  question_id INT NOT NULL,
  `text` TEXT NOT NULL,
  `type` ENUM('INCORRECT', 'CORRECT') NOT NULL,
  FOREIGN KEY (question_id) REFERENCES Question (id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);