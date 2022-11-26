package com.moontech.healthyfood.models.requests;

import com.moontech.healthyfood.enums.Genre;
import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Objeto de entrada para la api de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Data
public class UserRequest implements Serializable {
  /** Identificador del usuario. */
  private long id;
  /** nombre del usuario. */
  @NotBlank private String username;
  /** Propiedad primer nombre. */
  @NotBlank private String firstName;
  /** Propiedad segundo nombre. */
  @NotBlank private String lastName;
  /** Propiedad para el correo. */
  @NotBlank private String email;
  /** Propiedad para el celular. */
  @NotBlank private String cel;
  /** Propiedad para el género. */
  @NotNull private Genre genre;
  /** Sucursal del empleado. */
  @NotNull private Long branchOfficeId;
  /** Roles del usuario. */
  private Set<Long> profiles;
  /** Contraseña */
  private String password;
}
