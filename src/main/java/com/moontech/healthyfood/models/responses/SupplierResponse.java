package com.moontech.healthyfood.models.responses;

import java.io.Serializable;
import lombok.Data;

/**
 * Salida de la API de Proveedores.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Data
public class SupplierResponse implements Serializable {
  /** Identificador del proveedor. */
  private Long id;
  /** Nombre del proveedor. */
  private String name;
  /** Propiedad para la empresa. */
  private String enterprise;
  /** Propiedad para el RFC. */
  private String rfc;
  /** Dirección del proveedor. */
  private String address;
  /** Teléfono del proveedor. */
  private String phone;
  /** Comentarios. */
  private String comments;
  /** Status del proveedor. */
  private boolean status;
}
