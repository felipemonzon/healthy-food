CREATE TABLE catalog_schedule_meal (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(50) NOT NULL,
  PRIMARY KEY PK_SCHEDULE (id)
);

INSERT INTO catalog_schedule_meal (description) VALUES
('DESAYUNO'),
('COMIDA'),
('CENA');
