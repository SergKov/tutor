DROP DATABASE IF EXISTS Tutor;

CREATE DATABASE Tutor;

USE Tutor;

CREATE TABLE User (
  id INT NOT NULL AUTO_INCREMENT,
  role ENUM('STUDENT', 'TUTOR') NOT NULL,
  email VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  name VARCHAR(20) NOT NULL,
  surname VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Quiz (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL UNIQUE,
  FOREIGN KEY(speciality_id) REFERENCES Speciality(id),
  FOREIGN KEY(owner_id) REFERENCES Quiz(id),
  PRIMARY KEY (id)
);

CREATE TABLE User_Quiz (
  quiz_id INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY(quiz_id) REFERENCES Quiz(id),
  FOREIGN KEY(user_id) REFERENCES `User`(id)
);

CREATE TABLE Question (
  id INT NOT NULL AUTO_INCREMENT,
  quiz_id INT NOT NULL,
  `text` TEXT NOT NULL,
  FOREIGN KEY (quiz_id) REFERENCES Quiz(id),
  PRIMARY KEY (id)
);

CREATE TABLE Answer (
  id INT NOT NULL AUTO_INCREMENT,
  question_id INT NOT NULL,
  `text` TEXT NOT NULL,
  `type` ENUM('INCORRECT', 'CORRECT') NOT NULL,
  FOREIGN KEY (question_id) REFERENCES Question (id),
  PRIMARY KEY (id)
);

CREATE TABLE Result (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  mark TINYINT NOT NULL,
  quiz_name VARCHAR(15) NOT NULL,
  creation_datetime TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES `User` (id),
  PRIMARY KEY (id)
);