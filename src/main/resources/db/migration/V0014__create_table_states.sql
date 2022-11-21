CREATE TABLE states (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UN_NAME (name)
);

INSERT INTO states(name) VALUES('Sinaloa');

