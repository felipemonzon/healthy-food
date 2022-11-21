package com.moontech.library.models.responses;

import com.moontech.library.enums.Genre;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Respuesta para la api de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Data
public class UserResponse implements Serializable {
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
  private String branchOfficeName;
  /** Identificador de la sucursal del empleado. */
  private long branchOfficeId;
  /** Roles del usuario. */
  private Set<AuthorityResponse> authorities;
}
