package com.moontech.healthyfood.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Objeto de entrada de la API de units.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Data
public class UnitRequest {
  /** Identificador de la unidad. */
  private Long id;
  /** Nombre de la unidad. */
  @NotBlank private String name;
  /** Abreviación de la unidad. */
  @NotBlank private String abbreviation;
  /** Status de la unidad. */
  private boolean status = true;
}
