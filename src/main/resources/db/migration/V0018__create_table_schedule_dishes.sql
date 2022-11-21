CREATE TABLE schedule_dishes (
  id BIGINT NOT NULL AUTO_INCREMENT,
  id_meal BIGINT NOT NULL,
  id_schedule_meal BIGINT NOT NULL,
  schedule DATE NOT NULL,
  status BOOL DEFAULT true,
  created_by VARCHAR(250) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_by VARCHAR(255) NOT NULL,
  updated_date TIMESTAMP NOT NULL,
  PRIMARY KEY PK_SCHEDULE_DISHES (id),
  CONSTRAINT FK_SCHEDULE_MEAL FOREIGN KEY (id_schedule_meal) REFERENCES catalog_schedule_meal(id),
  CONSTRAINT FK_MEALS FOREIGN KEY (id_meal) REFERENCES meals(id)
);
