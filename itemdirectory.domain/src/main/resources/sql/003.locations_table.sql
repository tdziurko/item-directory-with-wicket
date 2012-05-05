
create table locations (
	id bigint(20) not null auto_increment,
	name varchar(255) not null,
	
	PRIMARY KEY (id),
	UNIQUE KEY unique_name (name)
);
