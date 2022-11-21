CREATE TABLE meals_ingredients (
	id_ingredient BIGINT NOT NULL,
    id_meal BIGINT NOT NULL,
	note varchar(100),
    quantity DECIMAL(10,2) NOT NULL,
    PRIMARY KEy (id_meal,id_ingredient),
	CONSTRAINT meals_ingredients FOREIGN KEY (id_ingredient) REFERENCES `ingredients`(id),
	CONSTRAINT ingredients_meals FOREIGN KEY (id_meal) REFERENCES `meals`(id)
);