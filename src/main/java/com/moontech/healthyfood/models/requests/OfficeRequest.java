package com.moontech.healthyfood.models.requests;

import javax.validation.constraints.*;
import lombok.Data;

/**
 * Clase usada de para guardar una sucursal.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 19 jul., 2022
 */
@Data
public class OfficeRequest {
  /** Identificador de la sucursal. */
  private Long id;
  /** Nombre de la sucursal. */
  @NotEmpty
  @Size(min = 1, max = 100)
  private String name;
  /** Dirección de la sucursal. */
  @NotBlank
  @Size(min = 1, max = 500)
  private String address;
  /** Teléfono de la sucursal. */
  @NotBlank
  @Size(min = 1, max = 100)
  private String phone;
  /** Propiedad para el identificador del encargado. */
  @NotNull
  @Min(value = 0)
  private Long idManager;
  /** Status de la sucursal. */
  private Boolean active = true;
}
