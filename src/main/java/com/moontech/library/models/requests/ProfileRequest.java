package com.moontech.library.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Entrada para los datos de perfile.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 20 nov., 2022
 */
@Data
public class ProfileRequest {
  /** Identificador del perfil. */
  private long id;
  /** Descripción del perfil. */
  @NotBlank private String name;
  /** Valor del perfil. */
  @NotBlank private String value;
}
