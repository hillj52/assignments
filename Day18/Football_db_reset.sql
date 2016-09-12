drop table if exists position;

create table position (
	id int primary key auto_increment,
	code varchar(2) not null,
	flex_eligible tinyint not null default 0
	);