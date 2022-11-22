package com.moontech.healthyfood.models.requests;

import com.moontech.healthyfood.enums.Genre;
import com.moontech.healthyfood.models.responses.OfficeResponse;
import lombok.Data;

import java.util.Set;

/**
 * Objeto de entrada para la api de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Data
public class User {
  /** Identificador del usuario. */
  private long id;
  /** nombre del usuario. */
  private String username;
  /** Propiedad primer nombre. */
  private String firstName;
  /** Propiedad segundo nombre. */
  private String lastName;
  /** Propiedad para el correo. */
  private String email;
  /** Propiedad para el celular. */
  private String cel;
  /** Propiedad para el género. */
  private Genre genre;
  /** Sucursal del empleado. */
  private OfficeResponse branchOffice;
  /** Roles del usuario. */
  Set<String> authorities;
}
