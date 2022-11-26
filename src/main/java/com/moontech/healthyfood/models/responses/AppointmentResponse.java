package com.moontech.healthyfood.models.responses;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Respuesta para las citas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 24 nov., 2022
 */
@Data
public class AppointmentResponse implements Serializable {
  /** Identificador de la cita. */
  private long id;
  /** Título de la cita */
  private String title;
  /** Fecha inicio. */
  private LocalDateTime start;
  /** Fecha final. */
  private LocalDateTime end;
}
