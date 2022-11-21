CREATE TABLE offices (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	address varchar(500) NOT NULL,
	phone varchar(100) NOT NULL,
	coordinate_x varchar(20) NOT NULL,
	coordinate_y varchar(20) NOT NULL,
	active bool NULL DEFAULT true,
	created_by varchar(250) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(255) NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT office_PK PRIMARY KEY (id),
	CONSTRAINT office_UN UNIQUE KEY (name)
);

INSERT INTO offices
(name, address, phone, coordinate_x, coordinate_y, active, created_by, created_date, updated_by, updated_date)
VALUES('HUMAYA', 'CALLE PRINCIPAL', '6671544056', '-12312', '+12312', 1, 'ADMIN', NOW(), 'ADMIN', NOW());
