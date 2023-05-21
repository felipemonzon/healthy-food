package com.moontech.healthyfood.models.requests;

import com.moontech.healthyfood.enums.Genre;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Datos con la petición de cliente
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 27 abr., 2023
 */
@Data
public class CustomerRequest implements Serializable {
  /** Identificador del usuario. */
  private long id;
  /** Propiedad primer nombre. */
  @NotBlank private String firstName;
  /** Propiedad para el apellido. */
  @NotBlank private String lastName;
  /** Propiedad para el correo. */
  @NotBlank @Email private String email;
  /** Propiedad para el celular. */
  @NotBlank
  @Size(min = 10, max = 10)
  private String cel;
  /** Propiedad para el género. */
  @NotNull private Genre genre;
}
