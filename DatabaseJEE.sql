-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 27 nov. 2020 à 20:17
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

-- --------------------------------------------------------

--
-- Detruit les utilisateurs 
--
/*
DROP USER 'adm'@'localhost';
DROP USER 'Pixel'@'localhost';
DROP USER 'Suz'@'localhost';
DROP USER 'Rob'@'localhost';
DROP USER 'Eliz'@'localhost';
DROP USER 'Edw'@'localhost';
DROP USER 'Norm'@'localhost';
*/
-- --------------------------------------------------------

--
-- Cree les utilisateurs 
--

CREATE USER 'adm'@'localhost' IDENTIFIED BY 'adm';
CREATE USER 'Pixel'@'localhost' IDENTIFIED BY 'jetit';
CREATE USER 'Suz'@'localhost' IDENTIFIED BY 'suzmdp';
CREATE USER 'Rob'@'localhost' IDENTIFIED BY 'robmdp';
CREATE USER 'Eliz'@'localhost' IDENTIFIED BY 'elizmdp';
CREATE USER 'Edw'@'localhost' IDENTIFIED BY 'edwmdp';
CREATE USER 'Norm'@'localhost' IDENTIFIED BY 'normmdp';

-- --------------------------------------------------------

--
-- Base de données : `st2eedb`
--

DROP DATABASE IF EXISTS ST2EEDB;
CREATE DATABASE ST2EEDB;

-- --------------------------------------------------------

--
-- Affecte les privileges aux utilisateurs 
--

GRANT ALL PRIVILEGES ON st2eedb.* TO 'adm'@'localhost'  WITH GRANT OPTION;
GRANT SELECT, INSERT, UPDATE ON st2eedb.* TO 'Pixel'@'localhost'  WITH GRANT OPTION;
GRANT SELECT, INSERT, UPDATE ON st2eedb.* TO 'Suz'@'localhost'  WITH GRANT OPTION;
GRANT SELECT, INSERT, UPDATE ON st2eedb.* TO 'Rob'@'localhost'  WITH GRANT OPTION;
GRANT SELECT, INSERT, UPDATE ON st2eedb.* TO 'Eliz'@'localhost'  WITH GRANT OPTION;
GRANT SELECT, INSERT, UPDATE ON st2eedb.* TO 'Edw'@'localhost'  WITH GRANT OPTION;
GRANT SELECT, INSERT, UPDATE ON st2eedb.* TO 'Norm'@'localhost'  WITH GRANT OPTION;

USE ST2EEDB;


-- --------------------------------------------------------

--
-- Genere les tables
--

CREATE TABLE  info_intern  (	
   intern_group varchar(255),
   info_intern_id  int PRIMARY KEY AUTO_INCREMENT,
   firstname  varchar(255),
   lastname  varchar(255),
   address varchar(255),
   skills  text,
   linkedin text,
   birthday  date
);

CREATE TABLE  visit_sheet  (
   visit_sheet_id  int PRIMARY KEY AUTO_INCREMENT,
   visit_planned  boolean,
   visit_done  boolean
);

CREATE TABLE  mission  (
   mission_id  int PRIMARY KEY AUTO_INCREMENT,
   year  int,
   start_mission DATE,
   end_mission DATE,
   report_title  varchar(255),
   comments_of_the_intern  text,
   mid_internship_meeting_info  text,
   soutenance boolean,
   key_word  text,
   eval_sheet_id  int,
   visit_sheet_id  int
);

CREATE TABLE  teacher  (
   teacher_id int PRIMARY KEY AUTO_INCREMENT,
   firstname  varchar(255),
   lastname  varchar(255),
   login  varchar(255),
   password  varchar(255)
);

CREATE TABLE  intern  (
   intern_id int PRIMARY KEY AUTO_INCREMENT,
   mission_id  int,
   info_intern_id  int,
   teacher_id  int
);

CREATE TABLE  eval_sheet  (
   eval_sheet_id  int PRIMARY KEY AUTO_INCREMENT,
   comments_of_supervisor  text,
   grade_tech  int,
   grade_com int,
   eval_sheet_done  boolean
);


ALTER TABLE  mission  ADD FOREIGN KEY ( eval_sheet_id ) REFERENCES  eval_sheet  ( eval_sheet_id );

ALTER TABLE  mission  ADD FOREIGN KEY ( visit_sheet_id ) REFERENCES  visit_sheet  ( visit_sheet_id );

ALTER TABLE  intern  ADD FOREIGN KEY ( mission_id ) REFERENCES  mission  ( mission_id );

ALTER TABLE  intern  ADD FOREIGN KEY ( info_intern_id ) REFERENCES  info_intern  ( info_intern_id );

ALTER TABLE  intern  ADD FOREIGN KEY ( teacher_id ) REFERENCES  teacher  ( teacher_id );

/*
INSERT INTO `st2eedb`.`teacher` (`firstname`, `lastname`, `login`, `password`) VALUES ('Jean', 'Petit', 'Pixel', 'jetit');
INSERT INTO `st2eedb`.`info_intern` (`firstname`, `lastname`, `skills`, `birthday`) VALUES ('Murielle', 'Bagneux', 'VBA, excel', '1995-10-10');
INSERT INTO `st2eedb`.`visit_sheet` (`visit_sheet_id`, `visit_planned`, `visit_done`) VALUES ('1', '1', '1');
INSERT INTO `st2eedb`.`mission` (`mission_id`, `year`, `visit_sheet_id`, `report_title`, `comments_of_the_intern`, `mid_internship_meeting_info`, `key_word`) VALUES ('1', '2018', '1', 'REPORT_TEST', 'Boring stage', 'yes', 'VBA, EXCEL');
INSERT INTO `st2eedb`.`eval_sheet` (`eval_sheet_id`, `comments_of_supervisor`, `grade_tech`, `eval_sheet_done`) VALUES ('1', 'AMZZING', '20', '1');
UPDATE `st2eedb`.`mission` SET `eval_sheet_id` = '1' WHERE (`mission_id` = '1');
INSERT INTO `st2eedb`.`intern` (`intern_id`, `mission_id`, `info_intern_id`, `teacher_id`) VALUES ('1', '1', '1', '1');
*/
-- --------------------------------------------------------

--
-- Genere une vue
--

CREATE OR REPLACE VIEW Affichage AS
	select
    info_intern.lastname AS lastname,
    info_intern.address AS address,
    info_intern.intern_group AS intern_group,
    mission.eval_sheet_id AS eval_sheet,
    mission.visit_sheet_id as visit_sheet, 
    mission.report_title as report_title,
    mission.soutenance as soutenance,
    mission.start_mission as start_mission,
    mission.end_mission as end_mission,
    eval_sheet.grade_tech as grade_tech,
    eval_sheet.grade_com as grade_com,
    visit_sheet.visit_planned as visit_planned,
    visit_sheet.visit_done as visit_done
    FROM
		intern
	LEFT OUTER JOIN teacher ON teacher.teacher_id = intern.teacher_id
    LEFT OUTER JOIN info_intern ON info_intern.info_intern_id = intern.info_intern_id
    LEFT OUTER JOIN mission ON mission.mission_id = intern.mission_id
    LEFT OUTER JOIN eval_sheet ON eval_sheet.eval_sheet_id = mission.eval_sheet_id
    LEFT OUTER JOIN visit_sheet ON visit_sheet.visit_sheet_id = mission.visit_sheet_id
    WHERE teacher.teacher_id = 1;

-- --------------------------------------------------------

--
-- Remplis les tables 
--


--
-- Déchargement des données de la table `eval_sheet`
--

INSERT INTO `eval_sheet` (`eval_sheet_id`, `comments_of_supervisor`, `grade_tech`, `grade_com`, `eval_sheet_done`) VALUES
(1, 'AMZZING', 1, 1, 1),
(2, 'wow', 1, 1, 1),
(3, NULL, 2, 2, 0),
(4, NULL, 3, 3, 0),
(5, NULL, 4, 4, 0),
(6, NULL, 5, 5, 0),
(7, NULL, 6, 6, 0),
(8, NULL, 7, 7, 0),
(9, NULL, 8, 8, 0),
(10, NULL, 9, 9, 0),
(11, NULL, 10, 10, 0),
(12, '', 1, 1, 0),
(13, NULL, 2, 2, 0),
(14, NULL, 3, 3, 0),
(15, NULL, 4, 4, 0),
(16, NULL, 5, 5, 0),
(17, NULL, 6, 6, 0),
(18, NULL, 7, 7, 0),
(19, NULL, 8, 8, 0),
(20, NULL, 9, 9, 0),
(21, NULL, 10, 10, 0),
(22, 'chabuleux', 12, 12, 1),
(23, NULL, 2, 2, 0),
(24, NULL, 3, 3, 0),
(25, NULL, 4, 4, 0),
(26, NULL, 5, 5, 0),
(27, NULL, 6, 6, 0),
(28, NULL, 7, 7, 0),
(29, NULL, 8, 8, 0),
(30, NULL, 9, 9, 0),
(31, NULL, 10, 10, 0),
(32, NULL, 1, 1, 0),
(33, NULL, 2, 2, 0),
(34, NULL, 3, 3, 0),
(35, NULL, 4, 4, 0),
(36, NULL, 5, 5, 0),
(37, NULL, 6, 6, 0),
(38, NULL, 7, 7, 0),
(39, NULL, 8, 8, 0),
(40, NULL, 9, 9, 0),
(41, NULL, 10, 10, 0),
(42, 'chatastique', 11, 11, 1),
(43, NULL, 2, 2, 0),
(44, NULL, 3, 3, 0),
(45, NULL, 4, 4, 0),
(46, NULL, 5, 5, 0),
(47, NULL, 6, 6, 0),
(48, NULL, 7, 7, 0),
(49, NULL, 8, 8, 0),
(50, NULL, 9, 9, 0),
(51, NULL, 10, 10, 0),
(52, NULL, 2, 2, 0),
(53, NULL, 2, 2, 0),
(54, NULL, 2, 2, 0);


--
-- Déchargement des données de la table `info_intern`
--

INSERT INTO `info_intern` (`intern_group`, `info_intern_id`, `firstname`, `lastname`, `address`, `skills`, `linkedin`, `birthday`) VALUES
('P1', 1, 'Murielle', 'Bagneux', 'rue de 1', 'VBA, excel', NULL, '1995-10-10'),
('P2', 2, NULL, 'Marchesseault', 'rue de 2', NULL, NULL, NULL),
('SE1', 3, NULL, 'Picard', 'rue de 1', NULL, NULL, NULL),
('SE1', 4, NULL, 'Bonsaint', 'rue de 2', NULL, NULL, NULL),
('SE1', 5, NULL, 'Patry', 'rue de 3', NULL, NULL, NULL),
('SE1', 6, NULL, 'Lereau', 'rue de 4', NULL, NULL, NULL),
('SE1', 7, NULL, 'Lapointe', 'rue de 5', NULL, NULL, NULL),
('SE2', 8, NULL, 'Bossard', 'rue de 6', NULL, NULL, NULL),
('SE2', 9, NULL, 'Richard', 'rue de 7', NULL, NULL, NULL),
('SE2', 10, NULL, 'Riviere', 'rue de 8', NULL, NULL, NULL),
('SE2', 11, NULL, 'Lalonde', 'rue de 9', NULL, NULL, NULL),
('SE2', 12, NULL, 'Boisvert', 'rue de 10', NULL, NULL, NULL),
('ME1', 13, NULL, 'Brochu', 'rue de 1', NULL, NULL, NULL),
('ME1', 14, NULL, 'Pariseau', 'rue de 2', NULL, NULL, NULL),
('ME1', 15, NULL, 'Vachon', 'rue de 3', NULL, NULL, NULL),
('ME1', 16, NULL, 'Bedard', 'rue de 4', NULL, NULL, NULL),
('ME1', 17, NULL, 'Pouchard', 'rue de 5', NULL, NULL, NULL),
('ME2', 18, NULL, 'Desrosiers', 'rue de 6', NULL, NULL, NULL),
('ME2', 19, NULL, 'Chartre', 'rue de 7', NULL, NULL, NULL),
('ME2', 20, NULL, 'Lapresse', 'rue de 8', NULL, NULL, NULL),
('ME2', 21, NULL, 'Chaloux', 'rue de 9', NULL, NULL, NULL),
('ME2', 22, NULL, 'Frappier', 'rue de 10', NULL, NULL, NULL),
('E1', 23, NULL, 'Duplanty', 'rue de 1', NULL, NULL, NULL),
('E1', 24, NULL, 'Renaud', 'rue de 2', NULL, NULL, NULL),
('E1', 25, NULL, 'Salmons', 'rue de 3', NULL, NULL, NULL),
('E1', 26, NULL, 'Poirier', 'rue de 4', NULL, NULL, NULL),
('E1', 27, NULL, 'Bellefeuille', 'rue de 5', NULL, NULL, NULL),
('E2', 28, NULL, 'Berthelette', 'rue de 6', NULL, NULL, NULL),
('E2', 29, NULL, 'Huard', 'rue de 7', NULL, NULL, NULL),
('E2', 30, NULL, 'Petit', 'rue de 8', NULL, NULL, NULL),
('E2', 31, NULL, 'Nadeau', 'rue de 9', NULL, NULL, NULL),
('E2', 32, NULL, 'Grimard', 'rue de 10', NULL, NULL, NULL),
('ED1', 33, NULL, 'Legault', 'rue de 1', NULL, NULL, NULL),
('ED1', 34, NULL, 'Dufresne', 'rue de 2', NULL, NULL, NULL),
('ED1', 35, NULL, 'Cloutier', 'rue de 3', NULL, NULL, NULL),
('ED1', 36, NULL, 'Barrette', 'rue de 4', NULL, NULL, NULL),
('ED1', 37, NULL, 'Thibault', 'rue de 5', NULL, NULL, NULL),
('ED2', 38, NULL, 'Barteaux', 'rue de 6', NULL, NULL, NULL),
('ED2', 39, NULL, 'Raymond', 'rue de 7', NULL, NULL, NULL),
('ED2', 40, NULL, 'Faure', 'rue de 8', NULL, NULL, NULL),
('ED2', 41, NULL, 'Desruisseaux', 'rue de 9', NULL, NULL, NULL),
('ED2', 42, NULL, 'Dostie', 'rue de 10', NULL, NULL, NULL),
('NO1', 43, NULL, 'LaGrande', 'rue de 1', NULL, NULL, NULL),
('NO1', 44, NULL, 'Primeau', 'rue de 2', NULL, NULL, NULL),
('NO1', 45, NULL, 'Beauchamps', 'rue de 3', NULL, NULL, NULL),
('NO1', 46, NULL, 'Bosse', 'rue de 4', NULL, NULL, NULL),
('NO1', 47, NULL, 'Gagnon', 'rue de 5', NULL, NULL, NULL),
('NO2', 48, NULL, 'Durand', 'rue de 6', NULL, NULL, NULL),
('NO2', 49, NULL, 'Parent', 'rue de 7', NULL, NULL, NULL),
('NO2', 50, NULL, 'Mousseau', 'rue de 8', NULL, NULL, NULL),
('NO2', 51, NULL, 'Chevrette', 'rue de 9', NULL, NULL, NULL),
('NO2', 52, NULL, 'Beauchamp', 'rue de 10', NULL, NULL, NULL);

--
-- Déchargement des données de la table `intern`
--

INSERT INTO `intern` (`intern_id`, `mission_id`, `info_intern_id`, `teacher_id`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 3, 3, 2),
(4, 4, 4, 2),
(5, 5, 5, 2),
(6, 6, 6, 2),
(7, 7, 7, 2),
(8, 8, 8, 2),
(9, 9, 9, 2),
(10, 10, 10, 2),
(11, 11, 11, 2),
(12, 12, 12, 2),
(13, 13, 13, 3),
(14, 14, 14, 3),
(15, 15, 15, 3),
(16, 16, 16, 3),
(17, 17, 17, 3),
(18, 18, 18, 3),
(19, 19, 19, 3),
(20, 20, 20, 3),
(21, 21, 21, 3),
(22, 22, 22, 3),
(23, 23, 23, 4),
(24, 24, 24, 4),
(25, 25, 25, 4),
(26, 26, 26, 4),
(27, 27, 27, 4),
(28, 28, 28, 4),
(29, 29, 29, 4),
(30, 30, 30, 4),
(31, 31, 31, 4),
(32, 32, 32, 4),
(33, 33, 33, 5),
(34, 34, 34, 5),
(35, 35, 35, 5),
(36, 36, 36, 5),
(37, 37, 37, 5),
(38, 38, 38, 5),
(39, 39, 39, 5),
(40, 40, 40, 5),
(41, 41, 41, 5),
(42, 42, 42, 5),
(43, 43, 43, 6),
(44, 44, 44, 6),
(45, 45, 45, 6),
(46, 46, 46, 6),
(47, 47, 47, 6),
(48, 48, 48, 6),
(49, 49, 49, 6),
(50, 50, 50, 6),
(51, 51, 51, 6),
(52, 52, 52, 6);

--
-- Déchargement des données de la table `mission`
--

INSERT INTO `mission` (`mission_id`, `year`, `start_mission`, `end_mission`, `report_title`, `comments_of_the_intern`, `mid_internship_meeting_info`, `soutenance`, `key_word`, `eval_sheet_id`, `visit_sheet_id`) VALUES
(1, 2018, '2001-01-01', '2002-01-02', 'REPORT_TEST', 'Boring stage', NULL, 0, 'VBA, EXCEL', 1, 1),
(2, 0, '2002-01-02', '2003-01-03', NULL, NULL, NULL, 1, NULL, 54, 54),
(3, 0, '2001-01-01', '2002-01-02', NULL, NULL, NULL, 1, NULL, 2, 2),
(4, 0, '2002-01-02', '2003-01-03', NULL, NULL, NULL, 0, NULL, 3, 3),
(5, 0, '2003-01-03', '2004-01-04', NULL, NULL, NULL, 1, NULL, 4, 4),
(6, 0, '2004-01-04', '2005-01-05', NULL, NULL, NULL, 0, NULL, 5, 5),
(7, 0, '2005-01-05', '2006-01-06', NULL, NULL, NULL, 1, NULL, 6, 6),
(8, 0, '2006-01-06', '2007-01-07', NULL, NULL, NULL, 1, NULL, 7, 7),
(9, 0, '2007-01-07', '2008-01-08', NULL, NULL, NULL, 0, NULL, 8, 8),
(10, 0, '2008-01-08', '2009-01-09', NULL, NULL, NULL, 1, NULL, 9, 9),
(11, 0, '2009-01-09', '2010-01-10', NULL, NULL, NULL, 1, NULL, 10, 10),
(12, 0, '2010-01-10', '2011-01-11', NULL, NULL, NULL, 0, NULL, 11, 11),
(13, 0, '2001-01-01', '2002-01-02', NULL, NULL, NULL, 1, NULL, 12, 12),
(14, 0, '2002-01-02', '2003-01-03', NULL, NULL, NULL, 1, NULL, 13, 13),
(15, 0, '2003-01-03', '2004-01-04', NULL, NULL, NULL, 1, NULL, 14, 14),
(16, 0, '2004-01-04', '2005-01-05', NULL, NULL, NULL, 1, NULL, 15, 15),
(17, 0, '2005-01-05', '2006-01-06', NULL, NULL, NULL, 1, NULL, 16, 16),
(18, 0, '2006-01-06', '2007-01-07', NULL, NULL, NULL, 1, NULL, 17, 17),
(19, 0, '2007-01-07', '2008-01-08', NULL, NULL, NULL, 1, NULL, 18, 18),
(20, 0, '2008-01-08', '2009-01-09', NULL, NULL, NULL, 1, NULL, 19, 19),
(21, 0, '2009-01-09', '2010-01-10', NULL, NULL, NULL, 1, NULL, 20, 20),
(22, 0, '2010-01-10', '2011-01-11', NULL, NULL, NULL, 0, NULL, 21, 21),
(23, 0, '2001-01-01', '2002-01-02', NULL, NULL, NULL, 0, NULL, 22, 22),
(24, 0, '2002-01-02', '2003-01-03', NULL, NULL, NULL, 1, NULL, 23, 23),
(25, 0, '2003-01-03', '2004-01-04', NULL, NULL, NULL, 0, NULL, 24, 24),
(26, 0, '2004-01-04', '2005-01-05', NULL, NULL, NULL, 1, NULL, 25, 25),
(27, 0, '2005-01-05', '2006-01-06', NULL, NULL, NULL, 0, NULL, 26, 26),
(28, 0, '2006-01-06', '2007-01-07', NULL, NULL, NULL, 1, NULL, 27, 27),
(29, 0, '2007-01-07', '2008-01-08', NULL, NULL, NULL, 0, NULL, 28, 28),
(30, 0, '2008-01-08', '2009-01-09', NULL, NULL, NULL, 1, NULL, 29, 29),
(31, 0, '2009-01-09', '2010-01-10', NULL, NULL, NULL, 0, NULL, 30, 30),
(32, 0, '2010-01-10', '2011-01-11', NULL, NULL, NULL, 1, NULL, 31, 31),
(33, 0, '2001-01-01', '2002-01-02', NULL, NULL, NULL, 0, NULL, 32, 32),
(34, 0, '2002-01-02', '2003-01-03', NULL, NULL, NULL, 1, NULL, 33, 33),
(35, 0, '2003-01-03', '2004-01-04', NULL, NULL, NULL, 0, NULL, 34, 34),
(36, 0, '2004-01-04', '2005-01-05', NULL, NULL, NULL, 1, NULL, 35, 35),
(37, 0, '2005-01-05', '2006-01-06', NULL, NULL, NULL, 0, NULL, 36, 36),
(38, 0, '2006-01-06', '2007-01-07', NULL, NULL, NULL, 1, NULL, 37, 37),
(39, 0, '2007-01-07', '2008-01-08', NULL, NULL, NULL, 0, NULL, 38, 38),
(40, 0, '2008-01-08', '2009-01-09', NULL, NULL, NULL, 1, NULL, 39, 39),
(41, 0, '2009-01-09', '2010-01-10', NULL, NULL, NULL, 0, NULL, 40, 40),
(42, 0, '2010-01-10', '2011-01-11', NULL, NULL, NULL, 1, NULL, 41, 41),
(43, 0, '2001-01-01', '2002-01-02', NULL, NULL, NULL, 0, NULL, 42, 42),
(44, 0, '2002-01-02', '2003-01-03', NULL, NULL, NULL, 1, NULL, 43, 43),
(45, 0, '2003-01-03', '2004-01-04', NULL, NULL, NULL, 0, NULL, 44, 44),
(46, 0, '2004-01-04', '2005-01-05', NULL, NULL, NULL, 1, NULL, 45, 45),
(47, 0, '2005-01-05', '2006-01-06', NULL, NULL, NULL, 0, NULL, 46, 46),
(48, 0, '2006-01-06', '2007-01-07', NULL, NULL, NULL, 1, NULL, 47, 47),
(49, 0, '2007-01-07', '2008-01-08', NULL, NULL, NULL, 0, NULL, 48, 48),
(50, 0, '2008-01-08', '2009-01-09', NULL, NULL, NULL, 1, NULL, 49, 49),
(51, 0, '2009-01-09', '2010-01-10', NULL, NULL, NULL, 0, NULL, 50, 50),
(52, 0, '2010-01-10', '2011-01-11', NULL, NULL, NULL, 1, NULL, 51, 51);

--
-- Déchargement des données de la table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `firstname`, `lastname`, `login`, `password`) VALUES
(1, 'Jean', 'Petit', 'Pixel', 'jetit'),
(2, 'Suzette', 'Board', 'Suz', 'suzmdp'),
(3, 'Robert', 'Wright', 'Rob', 'robmdp'),
(4, 'Elizabeth', 'Kirby', 'Eliz', 'elizmdp'),
(5, 'Edward', 'Dorman', 'Edw', 'edwmdp'),
(6, 'Norma', 'Prange', 'Norm', 'normmdp');

--
-- Déchargement des données de la table `visit_sheet`
--

INSERT INTO `visit_sheet` (`visit_sheet_id`, `visit_planned`, `visit_done`) VALUES
(1, 1, 1),
(2, 0, 1),
(3, 1, 1),
(4, 1, 1),
(5, 1, 0),
(6, 0, 1),
(7, 1, 1),
(8, 1, 0),
(9, 1, 0),
(10, 1, 0),
(11, 1, 1),
(12, 1, 1),
(13, 1, 0),
(14, 1, 1),
(15, 1, 0),
(16, 1, 1),
(17, 1, 0),
(18, 1, 1),
(19, 1, 1),
(20, 1, 1),
(21, 0, 0),
(22, 1, 1),
(23, 0, 0),
(24, 1, 1),
(25, 0, 0),
(26, 1, 1),
(27, 0, 0),
(28, 1, 1),
(29, 0, 0),
(30, 1, 1),
(31, 0, 0),
(32, 1, 1),
(33, 0, 0),
(34, 1, 1),
(35, 0, 0),
(36, 1, 1),
(37, 0, 0),
(38, 1, 1),
(39, 0, 0),
(40, 1, 1),
(41, 0, 0),
(42, 1, 1),
(43, 0, 0),
(44, 1, 1),
(45, 0, 0),
(46, 1, 1),
(47, 0, 0),
(48, 1, 1),
(49, 0, 0),
(50, 1, 1),
(51, 0, 0),
(52, 0, 0),
(53, 0, 0),
(54, 0, 0);
