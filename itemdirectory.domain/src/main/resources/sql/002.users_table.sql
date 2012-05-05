

create table users (
	id bigint(20) not null auto_increment,
	login varchar(255) not null,
	password varchar(255) not null,
	
	PRIMARY KEY (id),
	UNIQUE KEY unique_users_login (login)

);
