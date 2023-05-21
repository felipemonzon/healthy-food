package com.moontech.healthyfood.models.responses;

import com.moontech.healthyfood.enums.Genre;
import lombok.Data;

/**
 * Respuesta de la api de clientes.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 27 abr., 2023
 */
@Data
public class CustomerResponse {
  /** Identificador del usuario. */
  private long id;
  /** Propiedad primer nombre. */
  private String firstName;
  /** Propiedad para el apellido. */
  private String lastName;
  /** Propiedad para el correo. */
  private String email;
  /** Propiedad para el celular. */
  private String cel;
  /** Propiedad para el género. */
  private Genre genre;
}
