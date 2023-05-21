package com.moontech.healthyfood.services;

import com.moontech.healthyfood.enums.Status;
import com.moontech.healthyfood.models.responses.AppointmentResponse;
import java.util.List;

/**
 * Reglas de negocio para la api de citas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 26 abr., 2023
 */
public interface AppointmentService {
  /**
   * Consulta todas las citas.
   *
   * @return lista de citas encontradas
   */
  List<AppointmentResponse> retrieve();

  /**
   * Consulta las citas de un cliente por su identificador y con status.
   *
   * @param customerId identificador del cliente
   * @param status status de la cita
   * @return citas encontradas
   */
  List<AppointmentResponse> findByCustomerAndStatus(final Long customerId, final Status status);
}
