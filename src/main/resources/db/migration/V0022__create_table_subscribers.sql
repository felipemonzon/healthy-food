CREATE TABLE subscribers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  invoice_branch VARCHAR(25) NOT NULL,
  initial_date TIMESTAMP NOT NULL,
  expired_date TIMESTAMP NOT NULL,
  card_number VARCHAR(20) NOT NULL,
  payment_type BIGINT NOT NULL,
  status VARCHAR(30) NOT NULL,
  subscription_cost DECIMAL(10,4) NOT NULL,
  id_user BIGINT NOT NULL,
  id_subscription_type BIGINT NOT NULL,
  PRIMARY KEY PK_SUBSCRIBER(id),
  CONSTRAINT FK_PAYMENT_TYPE FOREIGN KEY (payment_type) REFERENCES payment_type(id),
  CONSTRAINT FK_SUBSCRIBER FOREIGN KEY (id_user) REFERENCES users(id),
  CONSTRAINT FK_SUBSCRIPTION_TYPE FOREIGN KEY (id_subscription_type) REFERENCES subscription_type(id)
);
