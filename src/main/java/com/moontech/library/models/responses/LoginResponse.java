package com.moontech.library.models.responses;

import com.moontech.library.enums.Genre;
import lombok.Data;

import java.util.Set;

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
}
