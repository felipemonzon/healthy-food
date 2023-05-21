package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.entities.CustomerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para clientes.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 25 abr., 2023
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
  /**
   * Consulta los datos del cliente por su nombre y apellido.
   *
   * @param firstname nombre del cliente
   * @param lastName apellido del cliente
   * @return datos del cliente en caso de existir
   */
  List<CustomerEntity> findByFirstNameAndLastName(final String firstname, final String lastName);
}
