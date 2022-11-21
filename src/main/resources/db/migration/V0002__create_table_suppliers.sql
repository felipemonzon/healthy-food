CREATE TABLE suppliers (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	enterprise varchar(50) NOT NULL,
	rfc varchar(20) NOT NULL,
	address varchar(500) NOT NULL,
	phone varchar(10) NOT NULL,
	comments varchar(500) NOT NULL,
	status bool NULL DEFAULT true,
	created_by varchar(250) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(255) NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT pk_suppliers PRIMARY KEY (id),
	CONSTRAINT unique_name UNIQUE KEY (name),
	CONSTRAINT unique_rfc UNIQUE KEY (rfc)
);

INSERT INTO suppliers
(name, enterprise, rfc, address, phone, comments, status, created_by, created_date, updated_by, updated_date)
VALUES('Costco', 'costco sa de cv', 'unrfc20202028', 'CALLE PRINCIPAL', '6671544056','Entrega puntal', 1, 'ADMIN', NOW(), 'ADMIN', NOW());
