CREATE TABLE meals (
	id BIGINT auto_increment NOT NULL PRIMARY KEY,
	name varchar(100) NOT NULL,
	description varchar(500) NOT NULL,
	status bool NULL DEFAULT true,
	created_by varchar(250) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(255) NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT unique_name UNIQUE KEY (name)
);