package com.moontech.healthyfood.models.responses;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Respuesta para la página inicial de usuarios.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 nov., 2022
 */
@Data
public class InitialUserResponse implements Serializable {
  /** Lista de sucursales. */
  private List<OfficeResponse> offices;
  /** Lista de roles o perfiles. */
  private List<AuthorityResponse> authorities;
}
