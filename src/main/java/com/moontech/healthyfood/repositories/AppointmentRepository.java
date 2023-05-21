package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.entities.AppointmentEntity;
import com.moontech.healthyfood.enums.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para citas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 26 abr., 2023
 */
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
  /**
   * Consulta las citas del cliente por status.
   *
   * @param customerId identificador del cliente
   * @param status estatus de la cita
   * @return lista de citas encontradas
   */
  List<AppointmentEntity> findByCustomerIdAndStatus(final Long customerId, final Status status);
}
