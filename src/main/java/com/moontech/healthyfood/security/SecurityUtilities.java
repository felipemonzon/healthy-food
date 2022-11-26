package com.moontech.healthyfood.security;

import com.moontech.healthyfood.models.responses.SecurityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilería para seguridad.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 25 nov., 2022
 */
@Slf4j
public abstract class SecurityUtilities {
  /** Caracteres permitidos en la generación de la contraseña. */
  private static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";
  /** Error al generar la contraseña. */
  private static final String ERROR_CODE = "Error en caracteres especiales";
  /** Contraseña generada. */
  private static final String PASSWORD_GENERATED = "Contraseña generada {}";
  /** Mascará para la tarjeta. */
  private static final String MASK_CARD = "XXXX XXXX XXXX ";
  /** Perfil de cliente. */
  public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

  /**
   * Encripta la contraseña.
   *
   * @param password contraseña a encriptar
   * @return contraseña encriptada
   */
  public static String passwordEncoder(final String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

  /**
   * Obtiene el genero del usuario.
   *
   * @param userCredentials credenciales de usuario
   * @return datos del usuario
   */
  public static SecurityResponse getUserData(final Authentication userCredentials) {
    return (SecurityResponse) userCredentials.getPrincipal();
  }

  /**
   * Obtiene le número de cliente.
   *
   * @param userCredentials credenciales de usuario
   * @return identificador de usuario
   */
  public static Long getUserId(final Authentication userCredentials) {
    SecurityResponse userCredential = (SecurityResponse) userCredentials.getPrincipal();
    return userCredential.getId();
  }

  /** Constructor */
  private SecurityUtilities() {}
}
