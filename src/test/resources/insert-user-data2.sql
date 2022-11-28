INSERT INTO offices (id, created_by, created_date, updated_by, updated_date, active, address, name, phone, manager)
VALUES(3, 'ADMIN',  NOW(), 'ADMIN',  NOW(), 1, 'barrancos', 'Far far away', '667152455', 3);

INSERT INTO users(id, id_office, username, phone, email, genre, password, created_by, created_date, updated_by, updated_date, first_name, last_name)
VALUES(3, 3, 'felipemonzon2705', '6671568899', 'felipemonzon2705@gmail.com', 1, '$2a$10$AIMscWdjETB4pcGs5yBqm.uGvOxp.BwnEdX65waHNrxvST8datYQS', 'ADMIN', NOW(), 'ADMIN', NOW(), 'Felipe','Monzon');


