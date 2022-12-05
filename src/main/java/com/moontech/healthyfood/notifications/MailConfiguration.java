package com.moontech.healthyfood.notifications;

import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * Objeto con las propiedades de correo.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since Dec 01, 2022
 */
@Data
@Builder
public class MailConfiguration {
  /** Encabezado de compra. */
  private final String payment;
  /** Encabezado de bienvenida. */
  private final String welcome;
  /** Destinatario del correo. */
  private final String mail;
  /** Plantilla de correo para usuarios de la webapp. */
  private final String templateUser;
  /** Plantilla de correo para clientes. */
  private final String templateCustomer;
  /** Mensaje predeterminado de ayuda. */
  private String helpMessage;
  /** Icono de venta. */
  private Resource checkImg;
  /** Logo. */
  private Resource logImg;
  /** Icono de Facebook. */
  private Resource facebookIcon;
  /** Icono de Whatsapp. */
  private Resource whatsIcon;
  /** Icono de EMAIL. */
  private Resource emailIcon;
  /** Plantillas de correo. */
  private Map<String, String> templates;
}
