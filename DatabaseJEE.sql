DROP DATABASE IF EXISTS ST2EEDB;
CREATE DATABASE ST2EEDB;
USE ST2EEDB;

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

INSERT INTO `st2eedb`.`teacher` (`firstname`, `lastname`, `password`) VALUES ('Jean', 'Petit', 'jetit');
INSERT INTO `st2eedb`.`info_intern` (`firstname`, `lastname`, `skills`, `birthday`) VALUES ('Murielle', 'Bagneux', 'VBA, excel', '1995-10-10');
INSERT INTO `st2eedb`.`visit_sheet` (`visit_sheet_id`, `visit_planned`, `visit_done`) VALUES ('1', '1', '1');
INSERT INTO `st2eedb`.`mission` (`mission_id`, `year`, `visit_sheet_id`, `report_title`, `comments_of_the_intern`, `mid_internship_meeting_info`, `key_word`) VALUES ('1', '2018', '1', 'REPORT_TEST', 'Boring stage', 'yes', 'VBA, EXCEL');
INSERT INTO `st2eedb`.`eval_sheet` (`eval_sheet_id`, `comments_of_supervisor`, `grade_tech`, `eval_sheet_done`) VALUES ('1', 'AMZZING', '20', '1');
UPDATE `st2eedb`.`mission` SET `eval_sheet_id` = '1' WHERE (`mission_id` = '1');
INSERT INTO `st2eedb`.`intern` (`intern_id`, `mission_id`, `info_intern_id`, `teacher_id`) VALUES ('1', '1', '1', '1');

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
    
    select * from Affichage;
    
    
    
    
	
	

