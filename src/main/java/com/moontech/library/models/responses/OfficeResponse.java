package com.moontech.library.models.responses;

import lombok.Data;

import java.io.Serializable;

/**
 * Respuesta para las APIS de sucursales.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 jul., 2022
 */
@Data
public class OfficeResponse implements Serializable {
  /** Identificador de la sucursal. */
  private Long id;
  /** Nombre de la sucursal. */
  private String name;
  /** Dirección de la sucursal. */
  private String address;
  /** Teléfono de contacto. */
  private String phone;
  /** Encargado de la sucursal. */
  private String manager;
  /** Identificador del manager. */
  private long managerId;
  /** Status de la sucursal. */
  private Boolean active;
}
