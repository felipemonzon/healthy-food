CREATE TABLE units (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	abbreviation varchar(10) NOT NULL,
	status bool NULL DEFAULT true,
	created_by varchar(250) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(255) NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT pk_parameters PRIMARY KEY (id),
	CONSTRAINT unique_description UNIQUE KEY (name)
);

INSERT INTO units
(name, abbreviation, status, created_by, created_date, updated_by, updated_date) VALUES
('Kilogramos', 'Kgr', 1, 'ADMIN', NOW(), 'ADMIN', NOW());

INSERT INTO units
(name, abbreviation, status, created_by, created_date, updated_by, updated_date) VALUES
('gramos', 'gr', 1, 'ADMIN', NOW(), 'ADMIN', NOW());

CREATE TABLE ingredients (
	id BIGINT AUTO_INCREMENT UNIQUE KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    supplier BIGINT NOT NULL,
    quantity DECIMAL(10,6) NOT NULL,
    unit BIGINT NOT NULL,
    raw DECIMAL(10,6) NOT NULL,
    cooked DECIMAL(10,6) NOT NULL,
    calories DECIMAL(10,6) NOT NULL,
    fat DECIMAL(10,6) NOT NULL,
    carbohydrates DECIMAL(10,6) NOT NULL,
    protein DECIMAL(10,6) NOT NULL,
    cost DECIMAL(10,6) NOT NULL,
    status bool NULL DEFAULT true,
    created_by varchar(250) NOT NULL,
    created_date timestamp NOT NULL,
    updated_by varchar(255) NOT NULL,
    updated_date timestamp NOT NULL,
    CONSTRAINT pk_parameters PRIMARY KEY (name, supplier),
    CONSTRAINT FK_supplier_ingredients FOREIGN KEY (supplier) REFERENCES suppliers(id),
    CONSTRAINT FK_unit_ingredients FOREIGN KEY (unit) REFERENCES units(id)
);