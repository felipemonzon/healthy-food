CREATE TABLE municipalities (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  id_state bigint NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT UN_NAME_STATE UNIQUE KEY (name, id_state),
  CONSTRAINT FK_STATE FOREIGN KEY (id_state) REFERENCES states (id)
);

INSERT INTO municipalities(name, id_state)VALUES('Culiacán', 1);


