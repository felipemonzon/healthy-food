package com.moontech.healthyfood.models.requests;

import com.moontech.healthyfood.enums.Status;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Clase para guardar Citas
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 24 nov., 2022
 */
@Data
public class AppointmentRequest implements Serializable {
  /** Identificador de la cita. */
  private long id;
  /** Título de la cita */
  @NotBlank private String title;
  /** Fecha inicio. */
  @NotNull private LocalDateTime start;
  /** Fecha final. */
  private LocalDateTime end;
  /** Propiedad para el status de la cita. */
  @NotNull private Status status;
}
