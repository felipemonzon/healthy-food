package com.moontech.library.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Objeto de entrada de la API de proveedores.
 *
 * @author Felipe Monzón
 * @enterprise Sano Pak
 * @since 15/09/21
 */
@Data
public class SupplierRequest {
  /** Identificador del proveedor. */
  private Long id;
  /** Nombre del proveedor. */
  @NotBlank private String name;
  /** Propiedad para la empresa. */
  @NotBlank private String enterprise;
  /** Propiedad para el RFC. */
  @NotBlank
  @Size(min = 14, max = 18)
  private String rfc;
  /** Dirección del proveedor. */
  @NotBlank private String address;
  /** Teléfono del proveedor. */
  @NotBlank
  @Size(min = 10, max = 10)
  private String phone;
  /** Comentarios. */
  @NotNull private String comments;
  /** Status del proveedor. */
  private boolean status = true;
}
