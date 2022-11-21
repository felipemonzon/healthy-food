package com.moontech.library.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Entrada de la API parameters-save
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Data
public class ParameterRequest {
  /** Identificador del parámetro. */
  @NotBlank private String id;
  /** Descripción del parámetro. */
  @NotBlank private String description;
  /** Valor del parámetro. */
  @NotBlank private String value;
  /** Status del parámetro. */
  private boolean status = true;
}
