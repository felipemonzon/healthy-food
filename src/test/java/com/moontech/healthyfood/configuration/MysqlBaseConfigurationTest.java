package com.moontech.healthyfood.configuration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Configuración base de Mysql para los test.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 04 nov., 2022
 */
@Testcontainers
public abstract class MysqlBaseConfigurationTest {
  /** Contenedor de Mysql. */
  private static final MySQLContainer<?> mysqlContainer;

  static {
    mysqlContainer =
        new MySQLContainer<>("mysql:latest")
            .withDatabaseName("healthyFood")
            .withUsername("root")
            .withPassword("root")
            .withReuse(Boolean.TRUE);
    mysqlContainer.start();
  }

  @DynamicPropertySource
  static void registerMySQLProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
    registry.add("spring.datasource.username", mysqlContainer::getUsername);
    registry.add("spring.datasource.password", mysqlContainer::getPassword);
  }
}
