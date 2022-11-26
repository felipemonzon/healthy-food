package com.moontech.healthyfood.models.responses;

import com.moontech.healthyfood.enums.Genre;
import java.util.Set;
import lombok.Data;

/**
 * Clase para retornar un login exitoso.
 *
 * @author Felipe Monzón
 * @since 17 jul., 2022
 */
@Data
public class LoginResponse {
  /** Identificador del usuario. */
  private Long id;
  /** Nombre de usuario. */
  private String username;
  /** Correo del usuario. */
  private String email;
  /** Propiedad primer nombre. */
  private String firstName;
  /** Propiedad segundo nombre. */
  private String lastName;
  /** Propiedad para el celular. */
  private String cel;
  /** Propiedad para el género. */
  private Genre genre;
  /** Nombre completo. */
  private String displayName;
  /** Perfiles */
  private Set<AuthorityResponse> profiles;
  /** Sucursal del empleado. */
  private String branchOfficeName;
  /** Identificador de la sucursal del empleado. */
  private Long branchOfficeId;
}
