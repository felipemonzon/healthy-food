CREATE TABLE suburbs (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  postal_code varchar(5) NOT NULL,
  delivery_cost decimal(5,2) NOT NULL DEFAULT '0.00',
  status bool DEFAULT true,
  id_municipality bigint NOT NULL,
  id_state BIGINT NOT NULL,
  created_by varchar(250) NOT NULL,
  created_date timestamp NOT NULL,
  updated_by varchar(255) NOT NULL,
  updated_date timestamp NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT UN_NAME_MUNICIPALITY UNIQUE KEY (name, id_municipality),
  CONSTRAINT FK_MUNICIPALITIES FOREIGN KEY (id_municipality) REFERENCES municipalities (id),
  CONSTRAINT FK_STATES FOREIGN KEY (id_state) REFERENCES states (id)
);

INSERT INTO suburbs (name, postal_code, delivery_cost, id_municipality, id_state, status, created_by, created_date, updated_by, updated_date) VALUES
('La Primavera', '80184', 30.50, 1, 1, 1, 'System', now(), 'System', now()),
('Las Quintas', '80050', 35.55, 1, 1, 1, 'System', now(), 'System', now());
