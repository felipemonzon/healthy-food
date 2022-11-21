CREATE TABLE subscription_type (
  id BIGINT NOT NULL AUTO_INCREMENT,
  code VARCHAR(4) NOT NULL,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL,
  number_dishes INT NOT NULL,
  max_dish_per_day INT NOT NULL,
  min_dish_per_day INT NOT NULL,
  subscription_cost_man DECIMAL(10,4) NOT NULL,
  subscription_cost_woman DECIMAL(10,4) NOT NULL,
  valid_days BIGINT NOT NULL,
  PRIMARY KEY PK_SUBSCRIPTION_TYPE (id)
);

INSERT INTO subscription_type (code,name,description,number_dishes,max_dish_per_day,min_dish_per_day,subscription_cost_man,subscription_cost_woman,valid_days) VALUES
('ATH1','ATHLETIC PAK','Para ti que tienes un objetivo definido',4,4,2,3000, 2100,7),
('ATH2','ATHLETIC PAK','Para ti que tienes un objetivo definido',4,4,2,5700, 4000, 14);
