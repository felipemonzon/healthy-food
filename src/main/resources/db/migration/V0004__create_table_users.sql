CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  phone varchar(10) NOT NULL,
  email varchar(100) NOT NULL,
  genre VARCHAR(1),
  id_office bigint NULL,
  password varchar(100) NOT NULL,
  active bool NULL DEFAULT true,
  created_by varchar(250) NOT NULL,
  created_date timestamp NOT NULL,
  updated_by varchar(255) NOT NULL,
  updated_date timestamp NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY `user_UN` (`phone`,`email`),
  CONSTRAINT FK_USER_OFFICE FOREIGN KEY (`id_office`) REFERENCES offices (`id`)
);

INSERT INTO users
(name, phone, email, genre, id_office, password, active, created_by, created_date, updated_by, updated_date)
VALUES('EDGAR ALARCON', '6671544056', 'EDGM.ALARCON@GMAIL.COM', 'M', 1, '$2a$10$AIMscWdjETB4pcGs5yBqm.uGvOxp.BwnEdX65waHNrxvST8datYQS', 1, 'ADMIN', NOW(), 'ADMIN', NOW());
