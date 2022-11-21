CREATE TABLE roles (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	value varchar(100) NOT NULL,
	isWeb bool NULL DEFAULT true,
	active bool NULL DEFAULT true,
	created_by varchar(250) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(255) NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT role_PK PRIMARY KEY (id),
	CONSTRAINT role_UN UNIQUE KEY (name,value)
);

INSERT INTO roles
(name, value, isWeb, active, created_by, created_date, updated_by, updated_date) VALUES
('ROLE_ADMIN', 'ADMIN', 1, 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('ROLE_CUSTOMER', 'Cliente', 1, 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('ROLE_NUTRITIONIST', 'Nutriólogo', 1, 1, 'ADMIN', NOW(), 'ADMIN', NOW());

