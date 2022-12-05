package com.moontech.healthyfood.notifications;

import java.util.Map;
import lombok.Data;

/**
 * Objeto para envío de correo.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since Dec 01, 2022
 */
@Data
public class Email {
  /** Receptor del correo. */
  private String to;
  /** Mapa de datos a enviar. */
  private Map<String, String> model;
}
