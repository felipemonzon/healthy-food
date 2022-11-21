CREATE TABLE orders (
  id BIGINT NOT NULL AUTO_INCREMENT,
  id_user BIGINT NOT NULL,
  id_schedule_dish BIGINT NOT NULL,
  order_date DATETIME NOT NULL,
  created_by varchar(250) NOT NULL,
  created_date timestamp NOT NULL,
  updated_by varchar(255) NOT NULL,
  updated_date timestamp NOT NULL,
  PRIMARY KEY PK_ORDERS (id),
  CONSTRAINT FK_ORDER_USER FOREIGN KEY (id_user) REFERENCES users (id),
  CONSTRAINT FK_ORDER_SCHEDULE_DISH FOREIGN KEY (id_schedule_dish) REFERENCES schedule_dishes (id)
);