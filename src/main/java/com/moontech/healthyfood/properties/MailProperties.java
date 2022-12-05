package com.moontech.healthyfood.properties;

import com.moontech.healthyfood.constants.ApiConstant;
import com.moontech.healthyfood.notifications.MailConfiguration;
import java.util.Map;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Propiedades para el correo de la aplicación.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 30 nov., 2022
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = ApiConstant.PROPERTIES_MAIL)
public class MailProperties {
  /** Encabezado del correo. */
  private String payment;
  /** Encabezado para registro. */
  private String welcome;
  /** Destinatario del correo. */
  private String from;
  /** Mensaje predeterminado de ayuda. */
  private String helpMessage;
  /** Imagen de venta. */
  @Value("classpath:/templates/hero-image-receipt.png")
  private Resource checkImg;
  /** Logo. */
  @Value("classpath:/templates/logo.png")
  private Resource logImg;
  /** Icono de Facebook. */
  @Value("classpath:/templates/facebook.png")
  private Resource facebookIcon;
  /** Icono de Whatsapp. */
  @Value("classpath:/templates/whatsapp.png")
  private Resource whatsIcon;
  /** Icono de EMAIL. */
  @Value("classpath:/templates/email.png")
  private Resource emailIcon;
  /** Plantilla de correo. */
  private Map<String, String> templates;

  /**
   * Configuración de las propiedades de correo.
   *
   * @return {@code MailConfiguration}
   */
  @Bean
  public MailConfiguration loadConfig() {
    return MailConfiguration.builder()
        .mail(this.from)
        .checkImg(this.checkImg)
        .payment(this.payment)
        .welcome(this.welcome)
        .helpMessage(this.helpMessage)
        .templates(templates)
        .facebookIcon(this.facebookIcon)
        .whatsIcon(this.whatsIcon)
        .emailIcon(this.emailIcon)
        .logImg(this.logImg)
        .build();
  }
}
