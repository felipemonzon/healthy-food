-- healthyFood.offices definition

CREATE TABLE `offices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `active` bit(1) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `manager` bigint NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO offices
(created_by, created_date, updated_by, updated_date, active, address, name, phone, manager)
VALUES('ADMIN', '2022-07-17 18:38:49', 'felipemonzon2705', '2022-11-14 20:35:56.649000000', 1, 'Muy lejos', 'barrancos 2', '667152455', 1);
