package com.moontech.library.models.responses;

import lombok.Data;

import java.io.Serializable;

/**
 * Respuesta para roles.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 nov., 2022
 */
@Data
public class AuthorityResponse implements Serializable {
  /** Identificador del perfil. */
  private long id;
  /** Descripción del perfil. */
  private String name;
  /** Valor del perfil. */
  private String value;
}
