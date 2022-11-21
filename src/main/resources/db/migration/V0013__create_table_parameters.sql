CREATE TABLE parameters (
	id varchar(50)  NOT NULL,
	description varchar(100) NOT NULL,
	value varchar(500) NOT NULL,
	status bool NULL DEFAULT true,
	created_by varchar(250) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(255) NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT pk_parameters PRIMARY KEY (id),
	CONSTRAINT unique_description UNIQUE KEY (description)
);

INSERT INTO parameters
(id, description, value, status, created_by, created_date, updated_by, updated_date) VALUES
('LCDS', 'Límite de costo diario (S)', '23.32', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('LCDM', 'Límite de costo diario (M)', '34.11', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('LCAL', 'Límite de calorías', '250', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('LG', 'Límite de grasas', '500', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('LCAR', 'Límite de carbohidratos', '350', 1, 'ADMIN', NOW(), 'ADMIN', NOW());
