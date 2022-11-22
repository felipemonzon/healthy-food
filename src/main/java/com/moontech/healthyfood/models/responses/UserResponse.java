package com.moontech.healthyfood.models.responses;

import com.moontech.healthyfood.enums.Genre;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;

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
