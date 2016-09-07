/*
 * Delete existing tables
 */
drop table if exists assignment;
drop table if exists grade; 
drop table if exists student_course_relationship; 
drop table if exists major_course_relationship;
drop table if exists course;
drop table if exists student;
drop table if exists instructor;
drop table if exists major;
/*
 * Create new tables
 */
create table major (
	id int primary key auto_increment,
	description varchar(30) not null,
	min_sat_score int not null
	);
create table instructor (
	id int primary key auto_increment,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	major_id int, 
	years_of_xp int not null, 
	is_tenured tinyint not null, 
	foreign key (major_id) 
		references major(id)
	);
delimiter '$$'
CREATE TRIGGER ins_years_check BEFORE INSERT ON instructor FOR EACH ROW
	IF NEW.years_of_xp < 0 THEN SET NEW.years_of_xp = 0;
	END IF;
$$
CREATE TRIGGER upd_years_check BEFORE UPDATE ON instructor FOR EACH ROW
	IF NEW.years_of_xp < 0 THEN SET NEW.years_of_xp = 0;
	END IF;
$$
delimiter ';'
create table course (
	id int primary key auto_increment,
	subject varchar(30) not null,
	number int not null,
	description varchar(50),
	instructor_id int,
	foreign key (instructor_id)
		references instructor(id)
	);
create table major_course_relationship (
	id int primary key auto_increment,
	major_id int not null,
	course_id int not null,
	foreign key (major_id)
		references major(id),
	foreign key (course_id)
		references course(id)
	);
create table student (
	id int primary key auto_increment,
	major_id int,
	gpa decimal(5.1),
	sat_score int not null,
	foreign key (major_id)
		references major(id)
	);
delimiter '$$'
CREATE TRIGGER ins_sat_low_check BEFORE INSERT ON student FOR EACH ROW
	IF NEW.sat_score < 400 THEN 
		SET NEW.sat_score = 400;
	END IF;
$$
CREATE TRIGGER ins_sat_high_check BEFORE INSERT ON student FOR EACH ROW
	IF NEW.sat_score > 1600 THEN 
		SET NEW.sat_score = 1600;
	END IF;
$$
CREATE TRIGGER upd_sat_low_check BEFORE UPDATE ON student FOR EACH ROW
	IF NEW.sat_score < 400 THEN 
		SET NEW.sat_score = 400;
	END IF;
$$
CREATE TRIGGER upd_sat_high_check BEFORE UPDATE ON student FOR EACH ROW
	IF NEW.sat_score > 1600 THEN 
		SET NEW.sat_score = 1600;
	END IF;
$$
delimiter ';'
create table student_course_relationship (
	id int primary key auto_increment,
	student_id int,
	course_id int,
	foreign key (student_id)
		references student(id),
	foreign key (course_id)
		references course(id)
	);
create table grade (
	id int primary key,
	description varchar(25) not null
	);
create table assignment (
	id int primary key auto_increment,
	student_id int,
	course_id int,
	foreign key (student_id)
		references student(id),
	foreign key (course_id)
		references course(id)
	);
/*
 *Insert data into tables
 */
insert major (description,min_sat_score) values ('General Business',800);
insert major (description,min_sat_score) values ('Accounting',1000);
insert major (description,min_sat_score) values ('Finance',1100);
insert major (description,min_sat_score) values ('Math',1300);
insert major (description,min_sat_score) values ('Engineering',1350);
insert major (description,min_sat_score) values ('Education',900);
insert major (description,min_sat_score) values ('General Studies',500);
 
select * from major;

insert instructor (first_name,last_name,major_id,years_of_xp,is_tenured) 
	values ('Ray','Lewis',1,52,1);
insert instructor (first_name,last_name,major_id,years_of_xp,is_tenured) 
	values ('Peter','Boulware',2,58,0);
insert instructor (first_name,last_name,major_id,years_of_xp,is_tenured) 
	values ('Terrel','Suggs',3,55,0);
insert instructor (first_name,last_name,major_id,years_of_xp,is_tenured) 
	values ('Jonathan','Ogden',4,75,1);
insert instructor (first_name,last_name,major_id,years_of_xp,is_tenured) 
	values ('Ed','Reed',5,20,1);
insert instructor (first_name,last_name,major_id,years_of_xp,is_tenured) 
	values ('Joe','Flacco',6,5,0);
insert instructor (first_name,last_name,major_id,years_of_xp,is_tenured) 
	values ('John','Harbaugh',7,18,1);
	
select * from instructor;

insert course (subject,number,description,instructor_id)
	values ('English',101,'Intro to Motivational Speaking',1);
insert course (subject,number,description,instructor_id)
	values ('English',102,'Intermediate Motivational Speaking',1);
insert course (subject,number,description,instructor_id)
	values ('English',103,'Advanced Motivational Speaking',1);
insert course (subject,number,description,instructor_id)
	values ('English',201,'Intro to Reading an Offense',1);
insert course (subject,number,description,instructor_id)
	values ('English',202,'Intermediate Reading an Offense',1);
insert course (subject,number,description,instructor_id)
	values ('English',203,'Advanced Reading an Offense',1);	
insert course (subject,number,description,instructor_id)
	values ('English',301,'Press Conference Basics',1);
insert course (subject,number,description,instructor_id)
	values ('English',302,'How to be an ESPN Analyst I',1);
insert course (subject,number,description,instructor_id)
	values ('English',303,'How to be an ESPN analyst II',1);
insert course (subject,number,description,instructor_id)
	values ('Math',201,'The Math Behind Pass Blocking',4);
insert course (subject,number,description,instructor_id)
	values ('Math',202,'The Math Behind Run Blocking',4);
insert course (subject,number,description,instructor_id)
	values ('Math',203,'How to use Angles to Block',4);
insert course (subject,number,description,instructor_id)
	values ('Math',204,'Advanced Math for Better Football',4);
insert course (subject,number,description,instructor_id)
	values ('Math',301,'Calculating Pass Trajectory',5);
insert course (subject,number,description,instructor_id)
	values ('Math',302,'The Physics of Interceptions',5);
insert course (subject,number,description,instructor_id)
	values ('Math',303,'Interception Return Dynamics',5);
insert course (subject,number,description,instructor_id)
	values ('Math',304,'Advanced Int Return Dynamics',5);
insert course (subject,number,description,instructor_id)
	values ('History',101,'History of the AFC North, Why the Ravens Dominate',7);
insert course (subject,number,description,instructor_id)
	values ('History',102,'2001-How the Best Defense of All Time Was Born',7);
insert course (subject,number,description,instructor_id)
	values ('History',103,'Playoff History, Why Can\'t The Bengals Win?',7);
insert course (subject,number,description,instructor_id)
	values ('Computer Science',311,'How to Program an Interception Machine',5);
insert course (subject,number,description,instructor_id)
	values ('Computer Science',312,'Programing a team to win',5);
insert course (subject,number,description,instructor_id)
	values ('Computer Science',313,'How The Bengals Programing went Haywire',5);
insert course (subject,number,description,instructor_id)
	values ('Computer Science',441,'Intro to Defensive Programing',5);
insert course (subject,number,description,instructor_id)
	values ('Computer Science',442,'Intermediate Defensive Programing',5);
insert course (subject,number,description,instructor_id)
	values ('Computer Science',443,'Advanced Defensive Programing',5);
insert course (subject,number,description,instructor_id)
	values ('Psychology',101,'The Psychology of the Salary Cap',2);
insert course (subject,number,description,instructor_id)
	values ('Psychology',102,'How Not to Pay Andy Dalton 100Mil',2);
insert course (subject,number,description,instructor_id)
	values ('Psychology',231,'How Marvin Lewis Got Overhyped After Baltimore',5);
insert course (subject,number,description,instructor_id)
	values ('Psychology',232,'Why Andy Dalton is not Elite Like Joe Flacco',5);
insert course (subject,number,description,instructor_id)
	values ('Education',221,'Basic Financial Education for the Salary Cap',3);
insert course (subject,number,description,instructor_id)
	values ('Education',222,'How to Rush the QB',3);
insert course (subject,number,description,instructor_id)
	values ('Education',223,'How not to Helmet to Helmet a WR',3);
insert course (subject,number,description,instructor_id)
	values ('Education',351,'How to Throw to Your Own Team',6);
insert course (subject,number,description,instructor_id)
	values ('Education',352,'Disecting a Defense',6);
insert course (subject,number,description,instructor_id)
	values ('Education',353,'How to be Elite',6);

select * from course;

insert major_course_relationship (major_id,course_id) values (1,1);
insert major_course_relationship (major_id,course_id) values (1,2);
insert major_course_relationship (major_id,course_id) values (1,3);
insert major_course_relationship (major_id,course_id) values (1,4);
insert major_course_relationship (major_id,course_id) values (1,5);
insert major_course_relationship (major_id,course_id) values (1,6);
insert major_course_relationship (major_id,course_id) values (1,7);
insert major_course_relationship (major_id,course_id) values (1,8);
insert major_course_relationship (major_id,course_id) values (1,9);
insert major_course_relationship (major_id,course_id) values (2,21);
insert major_course_relationship (major_id,course_id) values (2,22);
insert major_course_relationship (major_id,course_id) values (2,23);
insert major_course_relationship (major_id,course_id) values (2,24);
insert major_course_relationship (major_id,course_id) values (2,25);
insert major_course_relationship (major_id,course_id) values (2,26);
insert major_course_relationship (major_id,course_id) values (3,31);
insert major_course_relationship (major_id,course_id) values (3,32);
insert major_course_relationship (major_id,course_id) values (3,33);
insert major_course_relationship (major_id,course_id) values (3,34);
insert major_course_relationship (major_id,course_id) values (3,35);
insert major_course_relationship (major_id,course_id) values (3,36);
insert major_course_relationship (major_id,course_id) values (4,10);
insert major_course_relationship (major_id,course_id) values (4,11);
insert major_course_relationship (major_id,course_id) values (4,12);
insert major_course_relationship (major_id,course_id) values (4,13);
insert major_course_relationship (major_id,course_id) values (4,14);
insert major_course_relationship (major_id,course_id) values (4,15);
insert major_course_relationship (major_id,course_id) values (4,16);
insert major_course_relationship (major_id,course_id) values (4,17);
insert major_course_relationship (major_id,course_id) values (5,21);
insert major_course_relationship (major_id,course_id) values (5,22);
insert major_course_relationship (major_id,course_id) values (5,23);
insert major_course_relationship (major_id,course_id) values (5,10);
insert major_course_relationship (major_id,course_id) values (5,11);
insert major_course_relationship (major_id,course_id) values (5,12);
insert major_course_relationship (major_id,course_id) values (6,31);
insert major_course_relationship (major_id,course_id) values (6,32);
insert major_course_relationship (major_id,course_id) values (6,33);
insert major_course_relationship (major_id,course_id) values (6,34);
insert major_course_relationship (major_id,course_id) values (6,35);
insert major_course_relationship (major_id,course_id) values (6,36);
insert major_course_relationship (major_id,course_id) values (6,1);
insert major_course_relationship (major_id,course_id) values (6,2);
insert major_course_relationship (major_id,course_id) values (6,3);
insert major_course_relationship (major_id,course_id) values (7,18);
insert major_course_relationship (major_id,course_id) values (7,19);
insert major_course_relationship (major_id,course_id) values (7,20);
insert major_course_relationship (major_id,course_id) values (7,27);
insert major_course_relationship (major_id,course_id) values (7,28);
insert major_course_relationship (major_id,course_id) values (7,29);

select c.subject, c.number, c.description as 'Class Description', 
	i.first_name as 'Ins First Name', i.last_name as 'Ins Last Name', 
	m.description as 'Major'
	from course c, major m, instructor i,
	major_course_relationship b 
	where b.major_id = m.id and b.course_id = c.id and c.instructor_id = i.id;