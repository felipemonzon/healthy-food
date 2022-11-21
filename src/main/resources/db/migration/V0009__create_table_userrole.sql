CREATE TABLE user_roles (
	id_user BIGINT NOT NULL,
	id_role BIGINT NOT NULL,
	CONSTRAINT user_roles_user FOREIGN KEY (id_user) REFERENCES users(id),
	CONSTRAINT user_roles_role FOREIGN KEY (id_role) REFERENCES roles(id)
);

INSERT INTO user_roles (id_user, id_role) VALUES(1, 1);
